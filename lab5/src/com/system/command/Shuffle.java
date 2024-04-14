package com.system.command;

import com.system.CollectionManager;
import com.system.exceptions.InvalidInput;
import com.system.exceptions.NoSuchCommand;
import com.system.exceptions.ScriptException;

public class Shuffle extends Command{
	private final CollectionManager collectionManager;
	
	public Shuffle(CollectionManager collectionManager) {
		super("shuffle", "перемешать элементы коллекции в случайном порядке");
		this.collectionManager = collectionManager;
	}
	
	/**
     * shuffle collection
     * 
     * @param argument must be empty to execute
     * @excrption NoSuchCommand if argument isn't empty
     */

	@Override
	public void execute(String argument) throws NoSuchCommand, ScriptException, InvalidInput {
		if (!argument.isEmpty()) {
            throw new NoSuchCommand();
        }else {
        	collectionManager.Shuffle();
        }
	}
	
}
