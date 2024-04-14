package com.system.command;

import com.system.CollectionManager;
import com.system.exceptions.*;

public class RemoveAt extends Command{
	private final CollectionManager collectionManager;
	
	public RemoveAt(CollectionManager collectionManager) {
		super("remove_at", "удалить элемент, находящийся в заданной позиции коллекции (index)");
		this.collectionManager = collectionManager;
	}

	@Override
	public void execute(String argument) throws NoSuchCommand, ScriptException, InvalidInput {
		if (argument.isEmpty()) {
            throw new NoSuchCommand();
        }
		collectionManager.RemoveAt(argument);
	}
	
}