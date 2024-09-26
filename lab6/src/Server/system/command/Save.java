package Server.system.command;

import common.exceptions.*;
import Server.system.CollectionManager;

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
	
	public String execute(String argument, Object object) throws NoSuchCommand {
        return collectionManager.Save();
	}
}
