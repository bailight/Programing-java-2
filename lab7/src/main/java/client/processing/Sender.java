package client.processing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import common.PullingRequest;
import common.datamodule.Request;

public class Sender {
    private final Socket socket;

    public Sender(Socket socket) {
        this.socket = socket;
    }

    public void sendObject(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(request);
        outputStream.flush();
        byte[] data = byteArrayOutputStream.toByteArray();
        socket.getOutputStream().write(data);
        outputStream.close();
        byteArrayOutputStream.close();
    }

    public void sendObject(PullingRequest request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(request);
        outputStream.flush();
        byte[] data = byteArrayOutputStream.toByteArray();
        socket.getOutputStream().write(data);
        outputStream.close();
        byteArrayOutputStream.close();
    }
}
