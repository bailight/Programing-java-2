package Server.system.command;

import java.io.IOException;

import common.datamodule.Vehicle;
import common.exceptions.*;
import Server.system.CollectionManager;

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
     * @excrption InvalidInput if argument isn't empty
     */
	
	@Override
	public String execute(String argument, Object object) throws NoSuchCommand, ScriptException, InvalidInput {
		try {
			return collectionManager.Update(argument, (Vehicle) object);
		} catch (InvalidInput e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegaValue e) {
			e.printStackTrace();
		}
		return "";
	}
}