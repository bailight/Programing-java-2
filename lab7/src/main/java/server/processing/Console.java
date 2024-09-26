package server.processing;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import common.datamodule.Request;
import common.datamodule.Response;
import common.datamodule.User;
import common.datamodule.Vehicle;
import common.exceptions.*;
import server.psql.TabCreator;
import server.psql.TabManager;
import server.psql.UserCreator;
import server.psql.UserManager;
import server.system.*;

public class Console {
    private final String Port;
    private final int connect_timeout = 500 * 1000;
    public static final String DATABASE_URL_HELIOS = "jdbc:postgresql://pg/studs";
    public static final String DATABASE_URL_LOCAL = "jdbc:postgresql://localhost:11001/test";
    private static final ExecutorService REQUEST_READING_POOL = Executors.newCachedThreadPool();
    private static final ExecutorService REQUEST_PROCESSING_POOL = Executors.newCachedThreadPool();
    private static final ExecutorService RESPONSE_SENDING_POOL = Executors.newFixedThreadPool(10);

    private Receiver receiver;
    private Sender sender;

    public Console(String port) {
        this.Port = port;
    }

	public void Start(){
        REQUEST_READING_POOL.execute(() -> {
            try {
                int port = Integer.parseInt(Port);
                Communicator communicator = new Communicator(port, connect_timeout);
                communicator.openServerSocket();
                while (true) {
                    Thread.sleep(1000);
                    communicator.handleClientConnection();
                    Connection connection = DriverManager.getConnection(DATABASE_URL_LOCAL,"Baili","aaa111...");
                    UserCreator userCreator = new UserCreator(connection);
                    ArrayList<User> users = new UserCreator(connection).init();
                    TabCreator tabCreator = new TabCreator(connection);
                    ArrayList<Vehicle> vehicles = new TabCreator(connection).init();
                    ReadWriteLock lock = new ReentrantReadWriteLock();
                    CollectionManager collectionManager = new CollectionManager(connection, new TabManager(connection, tabCreator));
                    collectionManager.setList(vehicles);
                    InputData inputData = new InputData();
                    new Commands(collectionManager, inputData);
                    UserManager userManager = new UserManager(connection, users, lock);
                    receiver = new Receiver(communicator.getClientSocket(),userManager);
                    sender = new Sender(communicator.getClientSocket());
                    decodeAndProcessCommand();
                }
            }catch (IOException | ClassNotFoundException | OpeningServerSocket | ConnectionError |
                    InterruptedException | NoSuchCommand | ScriptException | InvalidInput | SQLException |
                    NoSuchAlgorithmException exception) {
                System.out.println(exception.getMessage());;
            }
        });
	}

    public void decodeAndProcessCommand() throws IOException, ClassNotFoundException, NoSuchCommand, ScriptException, InvalidInput, NoSuchAlgorithmException {
        REQUEST_PROCESSING_POOL.submit(() -> {
            Object request = null;
            try {
                request = receiver.receive();
                Response response = receiver.processRequest(request);
                    RESPONSE_SENDING_POOL.submit(() -> {
                        try {
                            sender.send(response);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    });
            } catch (IOException | ClassNotFoundException | ScriptException | NoSuchCommand | NoSuchAlgorithmException |
                     InvalidInput e) {
                System.out.println(e.getMessage());
            }
        });
//        Object request = receiver.receive();
//        Response response = receiver.processRequest(request);
//        sender.send(response);
    }
}
