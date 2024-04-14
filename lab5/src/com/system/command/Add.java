package com.system.command;

import java.io.IOException;

import com.system.CollectionManager;
import com.system.exceptions.*;

public class Add extends Command{
	private final CollectionManager collectionManager;
	
	public Add(CollectionManager collectionManager) {
		super("add", "добавить новый элемент в коллекцию");
		this.collectionManager = collectionManager;
	}

	 /**
     * Add a java object to the collection
     * 
     * @param argument must be empty to execute
     * @excrption InvalidInput if argument isn't empty
     */
	
	@Override
	public void execute(String argument) throws InvalidInput {
		if(!argument.isEmpty()) {
			throw new InvalidInput(); 
		}
		try {
			collectionManager.Add();
		} catch (InvalidInput e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegaValue e) {
			e.printStackTrace();
		}
		
	}
	
}
