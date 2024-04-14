package com.system.command;

import com.system.exceptions.*;

/**
 * parent of all commands
 */

public abstract class Command {
	private final String name;
    private final String description;
    
    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public abstract void execute(String argument) throws NoSuchCommand, ScriptException, InvalidInput;

}
