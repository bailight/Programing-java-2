package server.system.command;

import java.io.IOException;

import common.datamodule.User;
import common.datamodule.Vehicle;
import common.exceptions.*;
import server.system.CollectionManager;

public class Update extends Command{
	private final CollectionManager collectionManager;
	
	public Update(CollectionManager collectionManager) {
		super("update", "обновить значение элемента коллекции, id которого равен заданному");
		this.collectionManager = collectionManager;
	}
	
	/**
     * Update a java object to the collection
     * 
     * @param argument must be empty to execute
     * @excription InvalidInput if argument isn't empty
     */
	
	@Override
	public String execute(String argument, Object object, User user) throws NoSuchCommand, ScriptException, InvalidInput {
		try {
			return collectionManager.Update(argument, (Vehicle) object, user);
		} catch (InvalidInput | IOException | IllegaValue e) {
			System.out.println(e.getMessage());
		}
		return "";
	}
}