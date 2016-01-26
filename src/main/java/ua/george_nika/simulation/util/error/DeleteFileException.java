package ua.george_nika.simulation.util.error;

@SuppressWarnings("unused")

public class DeleteFileException extends UserFriendlyException {
    public DeleteFileException() {
    }

    public DeleteFileException(String message) {
        super(message);
    }

    public DeleteFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteFileException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
