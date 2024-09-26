package client.system;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import common.exceptions.ConnectionError;
import common.exceptions.NotInDeclaredLimits;

public class Communicator {

	private final String host;
    private final int port;
    private final int reconnectionTimeout;
    private int reconnectionAttempts;
    private final int maxReconnectionAttempts;
    private SocketChannel socketChannel;

    public Communicator(String host, int port, int reconnectionTimeout, int maxReconnectionAttempts) {
        this.host = host;
        this.port = port;
        this.reconnectionTimeout = reconnectionTimeout;
        this.maxReconnectionAttempts = maxReconnectionAttempts;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    private void connectToLoadBalancer() throws ConnectionError, NotInDeclaredLimits{
        try {
//            if (reconnectionAttempts >= 1) System.out.println("Повторное подключение к серверу...");
            socketChannel = SocketChannel.open(new InetSocketAddress(host, port));
        } catch (IllegalArgumentException exception) {
        	throw new NotInDeclaredLimits("Адрес сервера неверно!");
        } catch (IOException exception) {
        	throw new ConnectionError("Произошла ошибка при подключении к серверу!");
        }
    }

    public void closeConnection() {
        try {
            if (socketChannel != null && socketChannel.isOpen()) {
                socketChannel.close();
            } else {
            	System.out.println("Канал сокета не инициализирован или уже закрыт.");
            }
        } catch (IOException e) {
        	System.out.println("Произошла ошибка при закрытии соединения сокета: " + e.getMessage());
        }
    }

    public void connect() {
        try {
            boolean processingStatus = true;
            while (processingStatus) {
                try {
                    connectToLoadBalancer();
                    processingStatus = false;
                } catch (ConnectionError exception) {
                    if (reconnectionAttempts >= maxReconnectionAttempts) {
                    	System.out.println("Превышено количество попыток подключения!");
                        break;
                    }
                    try {
                        Thread.sleep(reconnectionTimeout);
                    } catch (IllegalArgumentException timeoutException) {
                    	System.out.println("Время соединения истекло: " + reconnectionTimeout);
                    } catch (Exception timeoutException) {
                    	System.out.println("Переподключение...");
                    }
                }
                reconnectionAttempts++;
            }
        } catch (NotInDeclaredLimits exception) {
        	System.out.println("Время соединения истекло");
        }
    }


}