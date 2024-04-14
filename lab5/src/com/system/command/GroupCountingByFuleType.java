package com.system.command;

import com.system.CollectionManager;
import com.system.exceptions.InvalidInput;
import com.system.exceptions.NoSuchCommand;
import com.system.exceptions.ScriptException;

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
	public void execute(String argument) throws NoSuchCommand, ScriptException, InvalidInput {
		if (!argument.isEmpty()) {
            throw new NoSuchCommand();
        }else {
        	collectionManager.GroupCountingByFuleType();
        }
	}
	
}
