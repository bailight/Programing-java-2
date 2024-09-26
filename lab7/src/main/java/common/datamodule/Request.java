package common.datamodule;

import java.io.Serializable;

public class Request implements Serializable {

    private static final long serialVersionUID = 3L;
    private final String command;
    private Serializable object;
    private String argument;
    private final User user;


    public Request(String command, Serializable object, String argument, User user) {
        this.command = command;
        this.object = object;
        this.argument = argument;
        this.user = user;
    }

    public Request(String command, User user) {
        this.command = command;
	    this.user = user;
    }

    public Request(String command, String argument, User user) {
    	this.command = command;
        this.argument = argument;
	    this.user = user;
    }

    public Request(String command, Serializable object, User user) {
    	this.command = command;
    	this.object = object;
	    this.user = user;
    }

    public String getCommand() {
        return command;
    }

    public Object getObject() {
        return object;
    }

    public String getArgument() {
        return argument;
    }

    public User getUser() {
    	return user;
    }

    public String getUsername() {
        return this.user.getName();
    }

    public String getPassword() {
        return this.user.getPassword();
    }

    public boolean isEmpty() {
        return command.isEmpty() && argument.isEmpty() && object == null;
    }


    @Override
    public String toString() {
        return "Request{"
                + " command ='" + command + '\''
                + ", argument ='" + argument + '\''
                + ", object =" + object
                + "} from " + user.getName();
    }
}