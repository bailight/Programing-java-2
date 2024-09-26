package server.system.command;

import java.util.HashMap;
import java.util.StringJoiner;

import common.datamodule.User;
import common.exceptions.*;

public class Help extends Command{
	 private final HashMap<String, Command> commands;
	
	public Help(HashMap<String, Command> commands) {
		super("help", "вывести справку по доступным командам");
		this.commands = commands;
	}
	
	/**
     * View help
     * 
     * @param argument must be empty to execute
     * @excrption NoSuchCommand if argument isn't empty
     */
	
	public String execute(String argument, Object object, User user) throws NoSuchCommand {
        StringJoiner message = new StringJoiner("\n");
        commands.forEach((k, v) -> message.add(k + ": " + v.getDescription()));
        return ("Список доступных команд:\n"+ message);
    }
}
