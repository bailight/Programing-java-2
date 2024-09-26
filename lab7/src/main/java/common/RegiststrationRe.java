package common;

public enum RegiststrationRe {
	REGISTERED("пользователь успешно зарегистрирован"),
    AUTHORIZED("вы вошли в свою учетную запись"),
    DENIED("неверный пароль или имя пользователя");

    private final String message;

    RegiststrationRe(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
