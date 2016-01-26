package ua.george_nika.simulation.util.error;

@SuppressWarnings("unused")

public class LoggerPoolWrongName extends NoUserFriendlyException {
    public LoggerPoolWrongName() {
    }

    public LoggerPoolWrongName(String message) {
        super(message);
    }

    public LoggerPoolWrongName(String message, Throwable cause) {
        super(message, cause);
    }

    public LoggerPoolWrongName(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
