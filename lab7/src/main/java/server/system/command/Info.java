package server.system.command;

import common.datamodule.User;
import common.exceptions.*;
import server.system.CollectionManager;

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
	public String execute(String arg, Object object, User user) throws NoSuchCommand {
		return collectionManager.Info();
	}

}

