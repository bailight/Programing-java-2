package com;

import java.io.IOException;

import com.system.CollectionManager;
import com.system.Commands;
import com.system.Console;
import com.system.InputData;
import com.system.exceptions.InvalidInput;

public class Main {

	public static void main(String[] args) {
		String path = null;
		if (System.getenv("HOMEPATH") != null) {
		  path = System.getenv("HOMEDRIVE") + System.getenv("HOMEPATH") + ("\\Desktop\\vehicle.xml");
		} else if (System.getenv("HOME") != null) {
		  path = System.getenv("HOME") + "/proga_2/lab5/vehicles.xml";
		}
		
		try {
            CollectionManager collectionManager = new CollectionManager(path);
            InputData inputData = new InputData();
            Commands commands = new Commands(collectionManager, inputData);
            Console console = new Console(commands);
            console.Start();
        } catch (InvalidInput e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
	}

}
