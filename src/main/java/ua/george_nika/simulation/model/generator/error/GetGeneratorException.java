package ua.george_nika.simulation.model.generator.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class GetGeneratorException extends NoUserFriendlyException {
    public GetGeneratorException() {
    }

    public GetGeneratorException(String message) {
        super(message);
    }

    public GetGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetGeneratorException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
