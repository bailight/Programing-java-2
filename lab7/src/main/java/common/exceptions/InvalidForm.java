package common.exceptions;

public class InvalidForm extends Exception{
    /**
	 *
	 */
	private static final long serialVersionUID = 1108L;

	public String getMessage() {
        return "Неверный Ввод в форме.";
    }
}
