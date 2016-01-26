package ua.george_nika.simulation.model.generator.error;

import ua.george_nika.simulation.util.error.UserFriendlyException;

/**
 * Created by george on 26.11.2015.
 */
public class NoSuchGenerator extends UserFriendlyException {
    public NoSuchGenerator() {
    }

    public NoSuchGenerator(String message) {
        super(message);
    }

    public NoSuchGenerator(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchGenerator(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
