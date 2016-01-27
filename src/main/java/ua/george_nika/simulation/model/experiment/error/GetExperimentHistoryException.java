package ua.george_nika.simulation.model.experiment.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class GetExperimentHistoryException extends NoUserFriendlyException {

    public GetExperimentHistoryException() {
        super();
    }

    public GetExperimentHistoryException(String message) {
        super(message);
    }

    public GetExperimentHistoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetExperimentHistoryException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
