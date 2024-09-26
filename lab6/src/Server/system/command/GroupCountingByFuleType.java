package Server.system.command;

import common.exceptions.*;
import Server.system.CollectionManager;

public class GroupCountingByFuleType extends Command{
	private final CollectionManager collectionManager;
	
	public GroupCountingByFuleType(CollectionManager collectionManager) {
		super("group_counting_by_fuel_type", "сгруппировать элементы коллекции по значению поля fuelType, вывести количество элементов в каждой группе");
		this.collectionManager = collectionManager;
	}
	
	/**
     * Grouped by fuel type
     * @param argument must be empty
     * @throws NoSuchCommandException if argument isn't empty
     */
	
	@Override
	public String execute(String argument, Object object) throws NoSuchCommand, ScriptException, InvalidInput {
		return collectionManager.GroupCountingByFuleType();
	}
	
}
