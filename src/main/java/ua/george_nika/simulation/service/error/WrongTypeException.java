package ua.george_nika.simulation.service.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class WrongTypeException extends NoUserFriendlyException {
    public WrongTypeException() {
    }

    public WrongTypeException(String message) {
        super(message);
    }

    public WrongTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongTypeException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
