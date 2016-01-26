package ua.george_nika.simulation.service.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class WrongIdException extends NoUserFriendlyException {
    public WrongIdException() {
    }

    public WrongIdException(String message) {
        super(message);
    }

    public WrongIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongIdException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
