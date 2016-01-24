package ua.george_nika.simulation.util.error;

/**
 * Created by george on 29.12.2015.
 */
public class DeleteFileException extends RuntimeException {
    public DeleteFileException() {
    }

    public DeleteFileException(String message) {
        super(message);
    }

    public DeleteFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
