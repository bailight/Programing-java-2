package com.system.exceptions;

@SuppressWarnings("serial")
public class ScriptException extends Exception {
    private final String string;

    public ScriptException(String string) {
        this.string = string;
    }

    public String getMessage() {
        return "Ошибка во время выполнения скрипта: " + string;
    }
}
