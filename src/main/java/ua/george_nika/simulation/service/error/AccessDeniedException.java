package ua.george_nika.simulation.service.error;

import ua.george_nika.simulation.util.error.UserFriendlyException;

@SuppressWarnings("unused")

public class AccessDeniedException extends UserFriendlyException {
    public AccessDeniedException() {
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDeniedException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
