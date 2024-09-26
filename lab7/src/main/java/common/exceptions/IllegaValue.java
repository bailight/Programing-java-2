package common.exceptions;

public class IllegaValue extends Exception{
	/**
	 *
	 */
	private static final long serialVersionUID = 1101L;
	private final String string;


	public IllegaValue(String string) {
		this.string = string;
	}

	public String getMessage() {
        return "Значение не соответствует формату. " + this.string;
    }
}
