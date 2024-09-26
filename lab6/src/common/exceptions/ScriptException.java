package common.exceptions;


public class ScriptException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1104L;
	private final String string;

    public ScriptException(String string) {
        this.string = string;
    }

    public String getMessage() {
        return "Ошибка во время выполнения скрипта: " + string;
    }
}
