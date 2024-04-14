package com.system.command;

import com.system.CollectionManager;
import com.system.exceptions.*;

public class RemoveAt extends Command{
	private final CollectionManager collectionManager;
	
	public RemoveAt(CollectionManager collectionManager) {
		super("remove_at", "удалить элемент, находящийся в заданной позиции коллекции (index)");
		this.collectionManager = collectionManager;
	}
	
	/**
     * Delete objects in collection based on index
     * 
     * @param argument must be empty to execute
     * @excrption InvalidInput if argument isn't empty
     */

	@Override
	public void execute(String argument) throws NoSuchCommand, ScriptException, InvalidInput {
		if (argument.isEmpty()) {
            throw new NoSuchCommand();
        }
		collectionManager.RemoveAt(argument);
	}
	
}