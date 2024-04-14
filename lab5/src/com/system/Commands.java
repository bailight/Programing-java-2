package com.system;

import java.util.ArrayList;

import com.system.command.*;

public class Commands {
    private final ArrayList<Command> commands = new ArrayList<>();

    public Commands(CollectionManager collectionManager, InputData inputData) {
    	commands.add(new Add(collectionManager));
        commands.add(new Clear(collectionManager));
        
        commands.add(new ExecuteScript(inputData, commands));

        commands.add(new Info(collectionManager));
        commands.add(new GroupCountingByFuleType(collectionManager));
        commands.add(new RemoveAt(collectionManager));
        commands.add(new RemoveByID(collectionManager));
        commands.add(new PrintDes(collectionManager));
        commands.add(new PrintDesFule(collectionManager));
        commands.add(new Shuffle(collectionManager));
        
        commands.add(new Save(collectionManager));
        
        commands.add(new Show(collectionManager));
        commands.add(new Update(collectionManager));
        commands.add(new Sort(collectionManager));
        commands.add(new Help(commands));
        
        commands.add(new Exit());
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }
}
