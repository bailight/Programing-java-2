package com.system.command;

import com.system.CollectionManager;
import com.system.exceptions.NoSuchCommand;

public class Info extends Command{
	private final CollectionManager collectionManager;
	
	public Info(CollectionManager collectionManager) {
		super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
		this.collectionManager = collectionManager;
	}
	
	/**
     * View info
     * 
     * @param argument must be empty to execute
     * @excrption NoSuchCommand if argument isn't empty
     */

	@Override
	public void execute(String arg) throws NoSuchCommand {
		if (!arg.isEmpty()) {
            throw new NoSuchCommand();
        }
		System.out.println(collectionManager.Info());
	}
	
}

