package ua.george_nika.simulation.model.generator.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class GetGeneratorHistoryException extends NoUserFriendlyException {

    public GetGeneratorHistoryException() {
        super();
    }

    public GetGeneratorHistoryException(String message) {
        super(message);
    }

    public GetGeneratorHistoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetGeneratorHistoryException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
