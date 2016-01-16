package ua.george_nika.simulation.service.error;

/**
 * Created by george on 01.01.2016.
 */
public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
