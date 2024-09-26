package Client.processing;

import java.io.IOException;

import Client.system.Communicator;
import common.Fill;
import common.datamodule.*;
import common.exceptions.IllegaValue;
import common.exceptions.InvalidInput;
import common.exceptions.NoSuchCommand;

public class CommandHandler {
	private final Communicator communicator;
	private final Fill fill = new Fill();

    public CommandHandler(Communicator communicator) {
        this.communicator = communicator;
    }
	
	public int executeCommand(String[] commandSet) {
		try {
			NoSuchCommand e = new  NoSuchCommand();
            if (commandSet[0].equals("help")|commandSet[0].equals("clear")|commandSet[0].equals("info")
        		|commandSet[0].equals("show")|commandSet[0].equals("shuffle")|commandSet[0].equals("sort")
        		|commandSet[0].equals("print_descending")|commandSet[0].equals("print_field_descending_fuel_type")
        		|commandSet[0].equals("group_counting_by_fuel_type")) {
                Request commandRequest = new Request(commandSet[0]);
                sendCommand(commandRequest);
                return 1;
            } else if (commandSet[0].equals("add")) {
                Request commandRequest = new Request(commandSet[0], fill.fillvehicle());
                sendCommand(commandRequest);
                System.out.println("----------Команда выполнена.");
                return 1;
            } else if (commandSet[0].equals("remove_by_id")|commandSet[0].equals("remove_at")) {
            	if (commandSet.length != 1) {
            		Request commandRequest = new Request(commandSet[0], commandSet[1]);
                    sendCommand(commandRequest);
                    System.out.println("----------Команда выполнена.");
                    return 1;
            	}else {
            		System.out.println(e.getMessage());
            		return 0;
            	}
            } else if (commandSet[0].equals("update_id")) {
            	if (commandSet.length != 1) {
            		Request commandRequest = new Request(commandSet[0], fill.fillvehicle(), commandSet[1]);
                    sendCommand(commandRequest);
                    System.out.println("----------Команда выполнена.");
                    return 1;
            	}else {
            		System.out.println(e.getMessage());
            		return 0;
            	}
            } else if (commandSet[0].equals("execute_script")) {
            	if (commandSet.length != 1) {
            		Request commandRequest = new Request(commandSet[0], commandSet[1]);
                    sendCommand(commandRequest);
                    System.out.println("----------Команда выполнена.");
                    return 1;
            	}else {
            		System.out.println(e.getMessage());
            		return 0;
            	}
            }
        	System.out.println("Команда не найдена.");
        	System.out.println("Введите 'help', чтобы просмотреть доступные команды.\n"
					+ "Для выхода введите 'exit'");
            return 0;
        } catch (IOException | ClassNotFoundException | InvalidInput | IllegaValue e) {
        	System.out.println("Исключение выполнения команды");
        }
        return 0;
    }
	
	private void sendCommand(Request request) throws IOException, ClassNotFoundException {
		communicator.connect();
        Sender sender = new Sender(communicator.getSocketChannel().socket());
        sender.sendObject(request);
        Receiver receiver = new Receiver(communicator.getSocketChannel().socket());
        Response response = receiver.receive();
        String answer = response.getRespose();
        System.out.println(answer);
    }
}