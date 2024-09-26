package Server.system.command;

import common.exceptions.*;
import Server.system.CollectionManager;

public class Sort extends Command{
	private final CollectionManager collectionManager;
	
	public Sort(CollectionManager collectionManager) {
		super("sort", "отсортировать коллекцию в естественном порядке");
		this.collectionManager = collectionManager;
	}
	
	/**
     * sort collection
     * 
     * @param argument must be empty to execute
     * @excrption NoSuchCommand if argument isn't empty
     */

	@Override
	public String execute(String argument, Object object) throws NoSuchCommand, ScriptException, InvalidInput {
		return collectionManager.Sort();
	}
	
}