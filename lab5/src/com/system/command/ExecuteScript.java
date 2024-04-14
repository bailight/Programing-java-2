package com.system.command;

import java.util.ArrayList;

import com.system.InputData;
import com.system.exceptions.*;

public class ExecuteScript extends Command{
	private final InputData inputData;
	private final ArrayList<Command> commands;

	
	public ExecuteScript(InputData inputData, ArrayList<Command> commands) {
		super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
		this.inputData = inputData;
		this.commands = commands;
	}

	/**
     * Enter the instructions in the script
     * 
     * @param argument mustn't be empty
     * @excrption NoSuchCommandException if argument is empty
     */
	
	@Override
	public void execute(String argument) throws NoSuchCommand, ScriptException, InvalidInput {
		if (argument.isEmpty()) {
            throw new NoSuchCommand();
        }
		String path = null;
		if (System.getenv("HOMEPATH") != null) {
		    path = System.getenv("HOMEDRIVE") + System.getenv("HOMEPATH");
		    path += "\\" + argument;
		} else if (System.getenv("HOME") != null) {
		    path = System.getenv("HOME");
		    path += "/" + argument;
		}
		ArrayList<String> commands = inputData.readScript(path);
		for (String command : commands) {
            if (command.split(" ").length == 2 && command.split(" ")[0].equals("execute_script") && command.split(" ")[1].equals(path)) {
                throw new IllegalArgumentException("Ошибка в цикл!");
            }
            String arg = "";
            for (int i = 0; i < this.commands.size(); i++) {
            	String inputCommand = command.split(" ")[0].toLowerCase();
            	Command command1 = this.commands.get(i);
                String name = String.valueOf(command1.getName());
                if (name.equals(inputCommand)) {
                	try {
                		if (command.split(" ").length > 1) {
                            arg = command.replaceFirst(inputCommand + " ", "");
                            command1.execute(arg);
                        }else {
                        	command1.execute(arg);
                        }
					} catch (NoSuchCommand e) {
						System.out.println(e.getMessage());
					} catch (ScriptException e) {
						System.out.println(e.getMessage());
					} catch (InvalidInput e) {
						System.out.println(e.getMessage());
					}
                }
            }
		}
	}
	
}