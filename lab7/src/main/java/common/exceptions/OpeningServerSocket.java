package common.exceptions;

public class OpeningServerSocket extends Exception {
    /**
	 *
	 */
	private static final long serialVersionUID = 1107L;
	private final String string;

    public OpeningServerSocket(String string) {
        this.string = string;
    }

    public String getMessage() {
        return "Ошибка во время выполнения скрипта: " + string;
    }
}