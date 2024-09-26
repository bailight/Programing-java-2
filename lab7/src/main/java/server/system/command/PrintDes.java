package server.system.command;

import common.datamodule.User;
import common.exceptions.*;
import server.system.CollectionManager;

public class PrintDes extends Command{
	private final CollectionManager collectionManager;
	
	public PrintDes(CollectionManager collectionManager) {
		super("print_descending", "вывести элементы коллекции в порядке убывания");
		this.collectionManager = collectionManager;
	}
	
	/**
     * Output collection in reverse order
     * 
     * @param argument must be empty to execute
     * @excrption NoSuchCommand if argument isn't empty
     */

	@Override
	public String execute(String argument, Object object, User user) throws NoSuchCommand, ScriptException, InvalidInput {
		return collectionManager.PrintDes();
	}
}