package ua.george_nika.simulation.service.error;

import ua.george_nika.simulation.util.error.UserFriendlyException;

@SuppressWarnings("unused")

public class EntityHistoryException extends UserFriendlyException {
    public EntityHistoryException() {
    }

    public EntityHistoryException(String message) {
        super(message);
    }

    public EntityHistoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityHistoryException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
