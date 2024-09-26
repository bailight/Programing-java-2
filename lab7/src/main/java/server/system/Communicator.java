package server.system;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import common.exceptions.*;

public class Communicator {
	private int port;
    private int soTimeout;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public Communicator(int port, int soTimeout) {
        this.port = port;
        this.soTimeout = soTimeout;
    }
    
    public void openServerSocket() throws OpeningServerSocket {
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(soTimeout);
            System.out.println("Сервер прослушивает порт " + port + "...");
        } catch (IllegalArgumentException exception) {
            throw new OpeningServerSocket("Порт вне зоны действия");
        } catch (IOException exception) {
            throw new OpeningServerSocket("Ошибка использования порта " + port + " !");
        }
    }
    
    public void handleClientConnection() throws ConnectionError, IOException {
        try {
        	System.out.println("Сервер слушает порт'" + port + "'...");
            clientSocket = serverSocket.accept();
            System.out.println("Соединение с клиентом успешно установлено!");
        } catch (SocketTimeoutException exception) {
        	System.out.println("Время соединения истекло!");
            System.exit(0);
        } catch (IOException exception) {
            throw new ConnectionError("Произошла ошибка при подключении к клиенту!");
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

}
