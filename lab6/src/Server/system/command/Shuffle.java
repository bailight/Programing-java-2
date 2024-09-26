package Server.system.command;

import common.exceptions.*;
import Server.system.CollectionManager;

public class Shuffle extends Command{
	private final CollectionManager collectionManager;
	
	public Shuffle(CollectionManager collectionManager) {
		super("shuffle", "перемешать элементы коллекции в случайном порядке");
		this.collectionManager = collectionManager;
	}
	
	/**
     * shuffle collection
     * 
     * @param argument must be empty to execute
     * @excrption NoSuchCommand if argument isn't empty
     */

	@Override
	public String execute(String argument, Object object) throws NoSuchCommand, ScriptException, InvalidInput {
		return collectionManager.Shuffle();
	}
	
}
