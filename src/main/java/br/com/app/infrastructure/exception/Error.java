package br.com.app.infrastructure.exception;

public enum Error {

	AGENDA_NOT_FOUND("AGENDA_NOT_FOUND", "Agenda not found!"),
	USER_NOT_FOUND("USER_NOT_FOUND", "User not found!"),
	USER_HAS_ALREADY_VOTED("USER_HAS_ALREADY_VOTED", "User has already voted!"),
	DATE_NOT_INFORMED("DATE_NOT_INFORMED", "Both dates must be informed!"),
	DATES_IS_EQUALS("DATES_IS_EQUALS", "Dates must be different betwen each other!"),
	DATE_IS_LESS_THAN_NOW("DATE_IS_LESS_THAN_NOW", "Dates must be higher than now!"),
	DATE_INITIAL_MUST_BE_LESS("DATE_INITIAL_MUST_BE_LESS", "Initial date must be less than end date!"),
	THERE_IS_VOTE_FOR_AGENDA("THERE_IS_VOTE_FOR_AGENDA", "Because there is already vote for this agenda, it can not be updated!");

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
