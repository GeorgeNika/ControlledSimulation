package ua.george_nika.simulation.service.error;

import ua.george_nika.simulation.util.error.UserFriendlyException;

@SuppressWarnings("unused")

public class ExperimentException extends UserFriendlyException {
    public ExperimentException() {
    }

    public ExperimentException(String message) {
        super(message);
    }

    public ExperimentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExperimentException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
