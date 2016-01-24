package ua.george_nika.simulation.service.error;

/**
 * Created by george on 04.01.2016.
 */
public class EntityHistoryException extends RuntimeException {
    public EntityHistoryException() {
    }

    public EntityHistoryException(String message) {
        super(message);
    }

    public EntityHistoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
