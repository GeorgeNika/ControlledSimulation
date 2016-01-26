package ua.george_nika.simulation.util.error;

@SuppressWarnings("unused")

public class NoEmptyLoggerInPool extends UserFriendlyException {
    public NoEmptyLoggerInPool() {
    }

    public NoEmptyLoggerInPool(String message) {
        super(message);
    }

    public NoEmptyLoggerInPool(String message, Throwable cause) {
        super(message, cause);
    }

    public NoEmptyLoggerInPool(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
