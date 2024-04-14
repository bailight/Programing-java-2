package com.system.command;

import com.system.exceptions.NoSuchCommand;

public class Exit extends Command{
	
	public Exit() {
		super("exit", "завершить программу (без сохранения в файл)");
	}

	@Override
	public void execute(String argument) throws NoSuchCommand {
		if (!argument.isEmpty()) {
            throw new NoSuchCommand();
        } else {
        	System.out.println("End of program");
        	System.exit(0);
        }
	}
}
