package Server.processing;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.datamodule.Request;
import common.datamodule.Response;
import common.exceptions.*;
import Server.system.*;

public class Console {
	private final String Port;
    private final int CONNECTION_TIMEOUT = 5000 * 1000;
    
    
    private Receiver receiver;
    private Sender sender;

    public Console(String port) {
        this.Port = port;
    }
	
	public void Start(){
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(() -> {
			try {
				String path = null;
				if (System.getenv("HOMEPATH") != null) {
					path = System.getenv("HOMEDRIVE") + System.getenv("HOMEPATH") + ("\\Desktop\\vehicle.xml");
				} else if (System.getenv("HOME") != null) {
					path = System.getenv("HOME") + "/proga_2/lab5/vehicles.xml";
				} else {
					System.out.println("Путь к хранилищу не существует, и программа была закрыта.");
					System.exit(0);
				}
				
                int port = Integer.parseInt(Port);
                Communicator communicator = new Communicator(port, CONNECTION_TIMEOUT);
                communicator.openServerSocket();
                while (true) {
                    Thread.sleep(1000);
                    communicator.handleClientConnection();
                    receiver = new Receiver(communicator.getClientSocket());
                    sender = new Sender(communicator.getClientSocket());
                    CollectionManager collectionManager = new CollectionManager(path);
                    InputData inputData = new InputData();
                    new Commands(collectionManager, inputData);
                    decodeAndProcessCommand();
                    collectionManager.Save();
                }
            }catch (IOException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException | OpeningServerSocket | ConnectionError e) {
                throw new RuntimeException();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (NoSuchCommand e) {
				e.getMessage();
			} catch (ScriptException e) {
				e.getMessage();
			} catch (InvalidInput e) {
				e.getMessage();
			}
		});
	}
	
	 public void decodeAndProcessCommand() throws IOException, ClassNotFoundException, NoSuchCommand, ScriptException, InvalidInput {
        Request request = receiver.receive();
        Response response = new Response(Commands.executeCommand(request));
        sender.send(response);
	 }
}
