package com.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.system.command.Command;
import com.system.exceptions.InvalidInput;
import com.system.exceptions.NoSuchCommand;
import com.system.exceptions.ScriptException;


public class Console {
	private final Commands commands;
	
	public Console(Commands commands) {
		this.commands = commands;
	}
	
	public void Start() throws InvalidInput, IOException{
        
		check : while (true) {
			System.out.print("Введите команды: ");
			BufferedReader isr = new BufferedReader(new InputStreamReader(System.in));
			String icommand = isr.readLine();
			if (icommand == null) {
			    break;
			}
			if (!icommand.trim().isEmpty()) {
				String inputCommand = icommand.split(" ")[0].toLowerCase();
                ArrayList<Command> commandList = commands.getCommands();
                String argument = "";
                for (int i = 0; i < commandList.size(); i++) {
                	Command command = commandList.get(i);
                    String name = String.valueOf(command.getName());
                    if (name.equals(inputCommand)) {
                    	try {
                    		if (icommand.split(" ").length > 1) {
                                argument = icommand.replaceFirst(inputCommand + " ", "");
                                command.execute(argument);
                            }else {
                            	command.execute(argument);
                            }
                    		System.out.println("----------Команда выполнена.");
						} catch (NoSuchCommand e) {
							System.out.println(e.getMessage());
						} catch (ScriptException e) {
							System.out.println(e.getMessage());
						} catch (InvalidInput e) {
							System.out.println(e.getMessage());
						}
                    	continue check;
                    }
                }
                System.out.println("Команда не найдена.");
			}else {
				System.out.println("Введите 'help', чтобы просмотреть доступные команды.\n"
						+ "Для выхода введите 'exit'");
            }
		}
		System.out.println("End of program");
	}
	
	
}
