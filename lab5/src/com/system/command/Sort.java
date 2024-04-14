package com.system.command;

import com.system.CollectionManager;
import com.system.exceptions.InvalidInput;
import com.system.exceptions.NoSuchCommand;
import com.system.exceptions.ScriptException;

public class Sort extends Command{
	private final CollectionManager collectionManager;
	
	public Sort(CollectionManager collectionManager) {
		super("sort", "отсортировать коллекцию в естественном порядке");
		this.collectionManager = collectionManager;
	}

	@Override
	public void execute(String argument) throws NoSuchCommand, ScriptException, InvalidInput {
		if (!argument.isEmpty()) {
            throw new NoSuchCommand();
        }else {
        	collectionManager.Sort();
        }
	}
	
}