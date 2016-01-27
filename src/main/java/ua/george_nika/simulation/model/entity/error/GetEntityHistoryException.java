package ua.george_nika.simulation.model.entity.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class GetEntityHistoryException extends NoUserFriendlyException {
    public GetEntityHistoryException() {
    }

    public GetEntityHistoryException(String message) {
        super(message);
    }

    public GetEntityHistoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetEntityHistoryException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
