package Server.system;


import java.util.HashMap;

import common.datamodule.Request;
import common.exceptions.InvalidInput;
import common.exceptions.NoSuchCommand;
import common.exceptions.ScriptException;
import Server.system.command.*;

public class Commands {
	private final static HashMap<String, Command> commands = new HashMap<>();
    

    public Commands(CollectionManager collectionManager, InputData inputData) {
    	commands.put("add", new Add(collectionManager));
        commands.put("clear", new Clear(collectionManager));
        
        commands.put("execute_script", new ExecuteScript(inputData));

        commands.put("info", new Info(collectionManager));
        commands.put("group_counting_by_fuel_type", new GroupCountingByFuleType(collectionManager));
        commands.put("remove_at", new RemoveAt(collectionManager));
        commands.put("remove_by_id", new RemoveByID(collectionManager));
        commands.put("print_descending", new PrintDes(collectionManager));
        commands.put("print_field_descending_fuel_type", new PrintDesFule(collectionManager));
        commands.put("shuffle", new Shuffle(collectionManager));
        
        commands.put("save", new Save(collectionManager));
        
        commands.put("show", new Show(collectionManager));
        commands.put("update_id", new Update(collectionManager));
        commands.put("sort", new Sort(collectionManager));
        commands.put("help", new Help(commands));
        
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }
    
    public static String executeCommand(Request request) throws NoSuchCommand, ScriptException, InvalidInput {
        String name = request.getCommand();
        Command command = commands.get(name);
        String argument = request.getArgument();
        Object object = request.getObject();
        return command.execute(argument, object);
    }
    
    
}
