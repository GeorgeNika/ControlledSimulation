package ua.george_nika.simulation.service.error;

import ua.george_nika.simulation.util.error.UserFriendlyException;

@SuppressWarnings("unused")

public class RunningHistoryException extends UserFriendlyException {
    public RunningHistoryException() {
    }

    public RunningHistoryException(String message) {
        super(message);
    }

    public RunningHistoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RunningHistoryException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
