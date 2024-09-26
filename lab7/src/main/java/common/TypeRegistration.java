package common;

public enum TypeRegistration {
	REGISTERED("пользователь успешно зарегистрирован"),
    AUTHORIZED("Вы успешно зашли в аккаунт"),
    DENIED("неверный пароль или имя пользователя");

    private final String message;

    TypeRegistration(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}