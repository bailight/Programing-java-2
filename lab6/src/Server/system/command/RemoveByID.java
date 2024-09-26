package Server.system.command;

import common.exceptions.*;
import Server.system.CollectionManager;

public class RemoveByID extends Command{
	private final CollectionManager collectionManager;
	
	public RemoveByID(CollectionManager collectionManager) {
		super("remove_by_id", "удалить элемент из коллекции по его id");
		this.collectionManager = collectionManager;
	}
	
	/**
     * Delete objects in collection based on id
     * 
     * @param argument must be empty to execute
     * @excrption InvalidInput if argument isn't empty
     */

	@Override
	public String execute(String argument, Object object) throws NoSuchCommand, ScriptException, InvalidInput {
		return collectionManager.RemoveByID(argument);
	}
	
}
