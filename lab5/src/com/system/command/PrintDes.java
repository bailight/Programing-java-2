package com.system.command;

import com.system.CollectionManager;
import com.system.exceptions.*;

public class PrintDes extends Command{
	private final CollectionManager collectionManager;
	
	public PrintDes(CollectionManager collectionManager) {
		super("print_descending", "вывести элементы коллекции в порядке убывания");
		this.collectionManager = collectionManager;
	}

	@Override
	public void execute(String argument) throws NoSuchCommand, ScriptException, InvalidInput {
		if (!argument.isEmpty()) {
            throw new NoSuchCommand();
        }else {
        	collectionManager.PrintDes();
        }
	}
}