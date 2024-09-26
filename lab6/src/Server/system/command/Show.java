package Server.system.command;

import common.exceptions.*;
import Server.system.CollectionManager;

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
	public String execute(String argument, Object object) throws NoSuchCommand{
		return collectionManager.Show();
	}
	
}
	