package ua.george_nika.simulation.model.entity.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class GetEntityInfoException extends NoUserFriendlyException {
    public GetEntityInfoException() {
    }

    public GetEntityInfoException(String message) {
        super(message);
    }

    public GetEntityInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetEntityInfoException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
