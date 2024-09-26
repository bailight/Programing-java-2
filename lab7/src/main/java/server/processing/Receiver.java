package server.processing;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

import common.PullingRequest;
import common.datamodule.Request;
import common.datamodule.Response;
import common.datamodule.User;
import common.exceptions.InvalidInput;
import common.exceptions.NoSuchCommand;
import common.exceptions.ScriptException;
import server.psql.UserManager;
import server.system.Commands;

public class Receiver {
    private final Socket socket;
    private final UserManager userManager;

    public Receiver(Socket socket, UserManager userManager) {
        this.socket = socket;
        this.userManager = userManager;
    }

    public Response processRequest(Object received) throws ScriptException, NoSuchCommand, NoSuchAlgorithmException, InvalidInput {
        if (received instanceof PullingRequest) {
            PullingRequest request = (PullingRequest) received;
            return (userManager.login(request));
        } else {
            Request request = (Request) received;
            User user = new User(request.getUsername(), PasswordEncode.encryptPassword(request.getPassword()), request.getUser().getSalt());
            if (userManager.checkUser(user)) {
                return new Response(Commands.executeCommand(request, user));
            } else {
                return new Response("команды могут выполняться только авторизованными пользователями");
            }
        }
    }


    public Object receive() throws IOException, ClassNotFoundException {
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Object object = objectInputStream.readObject();
        return object;
    }


}
