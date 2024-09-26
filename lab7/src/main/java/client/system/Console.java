package client.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import client.processing.CommandHandler;
import common.datamodule.User;
import common.exceptions.*;


public class Console extends Thread{

    private static final int MAX_RECONNECTION_ATTEMPTS = 5;
    private static final int RECONNECTION_TIMEOUT = 5 * 1000;

	public Console() {}

	public static void Start(String Host, int Port) throws InvalidInput, IOException{
		 try {
			 Communicator communicator = new Communicator(Host, Port, RECONNECTION_TIMEOUT, MAX_RECONNECTION_ATTEMPTS);
			 CommandHandler commandHandler = new CommandHandler(communicator);
			 Registration registration = new Registration(communicator);
			 User user = registration.checkRegist();
			 while (true) {
				 System.out.print("Введите команды: ");
				 BufferedReader isr = new BufferedReader(new InputStreamReader(System.in));
				 String icommand = isr.readLine();
				 if (icommand == null) {
					 System.out.println("\nEnd of program");
					 System.exit(0);
				 }else if(icommand.equals("exit")){
					 System.out.println("End of program");
					 System.exit(0);
				 }else {
					 String[] inputCommand = icommand.trim().toLowerCase().split(" ");
					 commandHandler.executeCommand(inputCommand, user);
				 }
			 }
		 } catch (IOException e) {
			System.out.println(e.getMessage());
		 }
    }

}