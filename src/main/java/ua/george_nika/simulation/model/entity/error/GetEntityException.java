package ua.george_nika.simulation.model.entity.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class GetEntityException extends NoUserFriendlyException {
    public GetEntityException() {
    }

    public GetEntityException(String message) {
        super(message);
    }

    public GetEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetEntityException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
