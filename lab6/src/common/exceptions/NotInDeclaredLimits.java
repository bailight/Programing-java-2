package common.exceptions;

public class NotInDeclaredLimits extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1106L;
	private final String string;


	public NotInDeclaredLimits(String string) {
		this.string = string;
	}

	public String getMessage() {
        return "Ошибка в соединение." + this.string;
    }
}
