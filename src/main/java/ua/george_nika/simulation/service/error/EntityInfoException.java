package ua.george_nika.simulation.service.error;

/**
 * Created by george on 04.01.2016.
 */
public class EntityInfoException extends RuntimeException {
    public EntityInfoException() {
    }

    public EntityInfoException(String message) {
        super(message);
    }

    public EntityInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}
