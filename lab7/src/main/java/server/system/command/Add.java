package server.system.command;

import java.io.IOException;
import java.sql.SQLException;

import common.datamodule.User;
import common.datamodule.Vehicle;
import common.exceptions.*;
import server.system.CollectionManager;

public class Add extends Command{
	private final CollectionManager collectionManager;
	
	public Add(CollectionManager collectionManager) {
		super("add", "добавить новый элемент в коллекцию");
		this.collectionManager = collectionManager;
	}

	 /**
     * Add a java object to the collection
     * 
     * @param argument must be empty to execute
     * @excrption InvalidInput if argument isn't empty
     */
	
	@Override
	public String execute(String argument, Object object, User user) throws InvalidInput {
		try {
			return collectionManager.Add((Vehicle) object, user);
		} catch (InvalidInput | IOException | IllegaValue e) {
            System.out.println(e.getMessage());
        }
		return "";
	}
	
}
