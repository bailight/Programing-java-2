package Server;

import java.io.IOException;

import Server.processing.Console;

public class Server {

	public static void main(String[] args) throws IOException {
		String port = "8888";
		Console consle = new Console(port);
		consle.Start();
	}
}
