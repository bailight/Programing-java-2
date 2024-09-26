package client.system;

import common.PullingRequest;
import common.check.FillUser;
import client.processing.Receiver;
import client.processing.Sender;
import common.TypeRegistration;
import common.datamodule.Response;
import common.datamodule.User;

import java.io.IOException;
import java.util.Objects;

public class Registration {
    private final Communicator communicator;
    private User user = null;

    public Registration(Communicator communicator) {
        this.communicator = communicator;
    }

    public User checkRegist(){
        while (true) {
            try{
                if (Objects.isNull(user)) {
                    Response response = null;
                    do {
                        FillUser userCheck = new FillUser();
                        user = new FillUser().build();
                        response = sendRequest(new PullingRequest(user.getName(), user.getPassword()));
                    } while (response.getStatusRegistration() != TypeRegistration.AUTHORIZED);
                    return user;
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Response sendRequest(PullingRequest request) throws IOException, ClassNotFoundException {
		communicator.connect();
        Sender sender = new Sender(communicator.getSocketChannel().socket());
        sender.sendObject(request);
        Receiver receiver = new Receiver(communicator.getSocketChannel().socket());
        Response response = receiver.receive();
        String answer = response.getTypeRegistration().getMessage();
        System.out.println(answer);
        return response;
    }

}
