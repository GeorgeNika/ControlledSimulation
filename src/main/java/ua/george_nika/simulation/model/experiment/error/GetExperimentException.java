package ua.george_nika.simulation.model.experiment.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class GetExperimentException extends NoUserFriendlyException {

    public GetExperimentException() {
        super();
    }

    public GetExperimentException(String message) {
        super(message);
    }

    public GetExperimentException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetExperimentException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
