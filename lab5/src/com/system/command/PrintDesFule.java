package com.system.command;

import com.system.CollectionManager;
import com.system.exceptions.InvalidInput;
import com.system.exceptions.NoSuchCommand;
import com.system.exceptions.ScriptException;

public class PrintDesFule extends Command{
	private final CollectionManager collectionManager;
	
	public PrintDesFule(CollectionManager collectionManager) {
		super("print_field_descending_fuel_type", "вывести значения поля fuelType всех элементов в порядке убывания");
		this.collectionManager = collectionManager;
	}

	@Override
	public void execute(String argument) throws NoSuchCommand, ScriptException, InvalidInput {
		if (!argument.isEmpty()) {
            throw new NoSuchCommand();
        }else {
        	collectionManager.PrintDesFule();
        }
	}
	
}