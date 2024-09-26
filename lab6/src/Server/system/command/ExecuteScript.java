package Server.system.command;

import java.util.ArrayList;

import common.datamodule.Request;
import common.exceptions.*;
import Server.system.Commands;
import Server.system.InputData;

public class ExecuteScript extends Command{
	private final InputData inputData;
	
	public ExecuteScript(InputData inputData) {
		super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
		this.inputData = inputData;
	}

	/**
     * Enter the instructions in the script
     * 
     * @param argument mustn't be empty
     * @excrption NoSuchCommandException if argument is empty
     */
	
	@Override
	public String execute(String argument, Object object) throws NoSuchCommand, ScriptException, InvalidInput {
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
        	String inputCommand = command.split(" ")[0].toLowerCase();
        	Request requsetScript = new Request(inputCommand, null, null);
        	return Commands.executeCommand(requsetScript);
		}
		return "";
	}
	
}