package server.system.command;

import common.datamodule.User;
import common.exceptions.*;
import server.system.CollectionManager;

import java.sql.SQLException;

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
	public String execute(String argument, Object object, User user) throws NoSuchCommand{
        try {
            return collectionManager.Show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
}
	