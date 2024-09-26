package Server.system.command;

import common.exceptions.*;

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
    
    public abstract String execute(String argument, Object object) throws NoSuchCommand, ScriptException, InvalidInput;

}
