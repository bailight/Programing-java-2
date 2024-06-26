package com.system.command;

import com.system.CollectionManager;
import com.system.exceptions.NoSuchCommand;

public class Save extends Command{
	private final CollectionManager collectionManager;
	
	public Save(CollectionManager collectionManager) {
		super("save", "сохранить коллекцию в файл");
		this.collectionManager = collectionManager;
	}
	
	/**
     * save collection
     * 
     * @param argument must be empty to execute
     * @excrption NoSuchCommand if argument isn't empty
     */
	
	public void execute(String argument) throws NoSuchCommand {
        if (!argument.isEmpty()) {
            throw new NoSuchCommand();
        }
        collectionManager.Save();
	}
}
