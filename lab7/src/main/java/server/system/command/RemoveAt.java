package server.system.command;

import common.datamodule.User;
import common.exceptions.*;
import server.system.CollectionManager;

public class RemoveAt extends Command{
	private final CollectionManager collectionManager;
	
	public RemoveAt(CollectionManager collectionManager) {
		super("remove_at", "удалить элемент, находящийся в заданной позиции коллекции (index)");
		this.collectionManager = collectionManager;
	}
	
	/**
     * Delete objects in collection based on index
     * 
     * @param argument must be empty to execute
     * @excrption InvalidInput if argument isn't empty
     */

	@Override
	public String execute(String argument, Object object, User user) throws NoSuchCommand, ScriptException, InvalidInput {
		return collectionManager.RemoveAt(argument);
	}
	
}