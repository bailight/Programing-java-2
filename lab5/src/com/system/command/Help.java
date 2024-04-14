package com.system.command;

import java.util.ArrayList;

import com.system.exceptions.*;

public class Help extends Command{
	 private final ArrayList<Command> commands;
	
	public Help(ArrayList<Command> commands) {
		super("help", "вывести справку по доступным командам");
		this.commands = commands;
	}
	
	/**
     * View help
     * 
     * @param argument must be empty to execute
     * @excrption NoSuchCommand if argument isn't empty
     */
	
	public void execute(String argument) throws NoSuchCommand {
        if (!argument.isEmpty()) {
            throw new NoSuchCommand();
        }
        System.out.println("Список доступных команд:");
        for (Command command : commands) {
            System.out.println(command.getName() + ": " + command.getDescription());
        }
    }
}
