package ua.george_nika.simulation.util.error;

@SuppressWarnings("unused")

public class ZipException extends UserFriendlyException {
    public ZipException() {
    }

    public ZipException(String message) {
        super(message);
    }

    public ZipException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZipException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
