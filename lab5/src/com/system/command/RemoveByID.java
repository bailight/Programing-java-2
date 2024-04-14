package com.system.command;

import com.system.CollectionManager;
import com.system.exceptions.*;

public class RemoveByID extends Command{
	private final CollectionManager collectionManager;
	
	public RemoveByID(CollectionManager collectionManager) {
		super("remove_by_id", "удалить элемент из коллекции по его id");
		this.collectionManager = collectionManager;
	}

	@Override
	public void execute(String argument) throws NoSuchCommand, ScriptException, InvalidInput {
		if (argument.isEmpty()) {
            throw new NoSuchCommand();
        }
		collectionManager.RemoveByID(argument);
	}
	
}
