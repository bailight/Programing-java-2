package Server.system.command;

import common.exceptions.*;
import Server.system.CollectionManager;

public class Clear extends Command{
	private final CollectionManager collectionManager;
	
	public Clear(CollectionManager collectionManager) {
		super("clear", "очистить коллекцию");
		this.collectionManager = collectionManager;
	}
	
	/**
     * Clean all objects in the collection
     * 
     * @param argument must be empty to execute
	 * @return 
     * @excrption InvalidInput if argument isn't empty
     */

	@Override
	public String execute(String argument, Object object) throws NoSuchCommand {
		return collectionManager.Clear();
	}
}
