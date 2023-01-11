package br.com.app.infrastructure.exception;

public enum Error {

	AGENDA_NOT_FOUND("AGENDA_NOT_FOUND", "Agenda not found!"),
	USER_NOT_FOUND("USER_NOT_FOUND", "User not found!"),
	USER_HAS_ALREADY_VOTED("USER_HAS_ALREADY_VOTED", "User has already voted!");

    private final String code;
    private final String message;

    private Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + message;
    }
}
