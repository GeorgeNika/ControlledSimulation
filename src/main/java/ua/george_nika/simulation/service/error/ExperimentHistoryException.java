package ua.george_nika.simulation.service.error;

import ua.george_nika.simulation.util.error.UserFriendlyException;

@SuppressWarnings("unused")

public class ExperimentHistoryException extends UserFriendlyException {
    public ExperimentHistoryException() {
    }

    public ExperimentHistoryException(String message) {
        super(message);
    }

    public ExperimentHistoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExperimentHistoryException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
