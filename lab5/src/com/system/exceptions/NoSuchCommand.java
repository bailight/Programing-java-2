package com.system.exceptions;


public class NoSuchCommand extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getMessage() {
        return "Команда с неправильным аргументом или не имеет аргумента.";
    }
}
