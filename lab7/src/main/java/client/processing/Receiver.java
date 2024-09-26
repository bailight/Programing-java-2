package client.processing;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import common.datamodule.Response;

public class Receiver {
    private final Socket socket;
    public Receiver(Socket socket) {
        this.socket = socket;
    }
    public Response receive() throws IOException, ClassNotFoundException {
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Object object = objectInputStream.readObject();
        return (Response) object;
    }
}
