package ua.george_nika.simulation.model.experiment.error;

import ua.george_nika.simulation.util.error.UserFriendlyException;

@SuppressWarnings("unused")

public class AlgorithmError extends UserFriendlyException {

    public AlgorithmError() {
        super();
    }

    public AlgorithmError(String message) {
        super(message);
    }

    public AlgorithmError(String message, Throwable cause) {
        super(message, cause);
    }

    public AlgorithmError(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
