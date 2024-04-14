package com.system.exceptions;

@SuppressWarnings("serial")
public class InvalidInput extends Exception {
    public String getMessage() {
        return "Неверный Ввод.";
    }
}
