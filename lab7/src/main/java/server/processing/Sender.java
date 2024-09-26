package server.processing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import common.datamodule.Response;

public class Sender {
    private final Socket socket;

    public Sender(Socket socket) {
        this.socket = socket;
    }

    public void send(Response response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(response);
        outputStream.flush();
        byte[] data = byteArrayOutputStream.toByteArray();
        socket.getOutputStream().write(data);
        outputStream.close();
        byteArrayOutputStream.close();
    }
}
