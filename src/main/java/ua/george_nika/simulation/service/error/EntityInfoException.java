package ua.george_nika.simulation.service.error;

import ua.george_nika.simulation.util.error.UserFriendlyException;

@SuppressWarnings("unused")

public class EntityInfoException extends UserFriendlyException {
    public EntityInfoException() {
    }

    public EntityInfoException(String message) {
        super(message);
    }

    public EntityInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityInfoException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
