package ua.george_nika.simulation.util.error;

/**
 * Created by george on 29.12.2015.
 */
public class ZipException extends RuntimeException {
    public ZipException() {
    }

    public ZipException(String message) {
        super(message);
    }

    public ZipException(String message, Throwable cause) {
        super(message, cause);
    }
}
