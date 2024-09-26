package Client;

import java.io.IOException;

import Client.system.Console;
import common.exceptions.InvalidInput;

public class Client {

	public static void main(String[] args) {
		try {
			Console.Start("localhost", 8888);
		} catch (InvalidInput | IOException e) {
			e.getMessage();
		}
	}

}
