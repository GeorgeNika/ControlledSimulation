package ua.george_nika.simulation.controller.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class NoExtraControllerRegistered extends NoUserFriendlyException {
    public NoExtraControllerRegistered() {
    }

    public NoExtraControllerRegistered(String message) {
        super(message);
    }

    public NoExtraControllerRegistered(String message, Throwable cause) {
        super(message, cause);
    }

    public NoExtraControllerRegistered(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
