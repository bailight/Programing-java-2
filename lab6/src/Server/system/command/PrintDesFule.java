package Server.system.command;

import common.exceptions.*;
import Server.system.CollectionManager;

public class PrintDesFule extends Command{
	private final CollectionManager collectionManager;
	
	public PrintDesFule(CollectionManager collectionManager) {
		super("print_field_descending_fuel_type", "вывести значения поля fuelType всех элементов в порядке убывания");
		this.collectionManager = collectionManager;
	}
	
	/**
     * Outputs a collection of gasoline types sorted in reverse order.
     * 
     * @param argument must be empty to execute
     * @excrption NoSuchCommand if argument isn't empty
     */

	@Override
	public String execute(String argument, Object object) throws NoSuchCommand, ScriptException, InvalidInput {
		return collectionManager.PrintDesFule();
	}
	
}