package ua.george_nika.simulation.service.error;

import ua.george_nika.simulation.util.error.UserFriendlyException;

@SuppressWarnings("unused")

public class GeneratorHistoryException extends UserFriendlyException {
    public GeneratorHistoryException() {
    }

    public GeneratorHistoryException(String message) {
        super(message);
    }

    public GeneratorHistoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneratorHistoryException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
