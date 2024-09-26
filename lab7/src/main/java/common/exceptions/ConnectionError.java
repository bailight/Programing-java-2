package common.exceptions;

public class ConnectionError extends Exception{

	private static final long serialVersionUID = 1103L;
	private final String string;


	public ConnectionError(String string) {
		this.string = string;
	}

	public String getMessage() {
        return "Ошибка в соединение." + this.string;
    }
}
