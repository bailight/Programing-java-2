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
