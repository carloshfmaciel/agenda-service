package br.com.app.infrastructure.errorhandling;

public enum Error {

	AGENDA_NOT_FOUND("AGENDA_NOT_FOUND", "Agenda not found!");

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
