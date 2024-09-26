package common.exceptions;

public class InvalidInput extends Exception {
    /**
	 *
	 */
	private static final long serialVersionUID = 1105L;

	public String getMessage() {
        return "Неверный Ввод.";
    }
}
