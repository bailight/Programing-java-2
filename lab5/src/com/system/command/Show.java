package com.system.command;

import com.system.CollectionManager;
import com.system.exceptions.NoSuchCommand;

public class Show extends Command{
	private final CollectionManager collectionManager;
	
	public Show(CollectionManager collectionManager) {
		super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
		this.collectionManager = collectionManager;
	}
	
	/**
     * display collection
     * 
     * @param argument must be empty to execute
     * @excrption NoSuchCommand if argument isn't empty
     */

	@Override
	public void execute(String argument) throws NoSuchCommand{
		if (!argument.isEmpty()) {
            throw new NoSuchCommand();
        }
        collectionManager.Show();
	}
	
}
	