package com.system.command;


import com.system.CollectionManager;
import com.system.exceptions.NoSuchCommand;

public class Clear extends Command{
	private final CollectionManager collectionManager;
	
	public Clear(CollectionManager collectionManager) {
		super("clear", "очистить коллекцию");
		this.collectionManager = collectionManager;
	}

	@Override
	public void execute(String argument) throws NoSuchCommand {
		if (!argument.isEmpty()) {
            throw new NoSuchCommand();
        }
		collectionManager.Clear();
	}
}
