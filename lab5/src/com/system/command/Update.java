package com.system.command;

import java.io.IOException;

import com.system.CollectionManager;
import com.system.exceptions.IllegaValue;
import com.system.exceptions.InvalidInput;
import com.system.exceptions.NoSuchCommand;
import com.system.exceptions.ScriptException;

public class Update extends Command{
	private final CollectionManager collectionManager;
	
	public Update(CollectionManager collectionManager) {
		super("update", "обновить значение элемента коллекции, id которого равен заданному");
		this.collectionManager = collectionManager;
	}
	
	/**
     * Update a java object to the collection
     * 
     * @param argument must be empty to execute
     * @excrption InvalidInput if argument isn't empty
     */
	
	@Override
	public void execute(String argument) throws NoSuchCommand, ScriptException, InvalidInput {
		if(argument.isEmpty()) {
			throw new InvalidInput();
		}
		try {
			collectionManager.Update(argument);
		} catch (InvalidInput e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegaValue e) {
			e.printStackTrace();
		}
	}
}