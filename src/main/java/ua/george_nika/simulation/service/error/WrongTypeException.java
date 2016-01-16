package ua.george_nika.simulation.service.error;

/**
 * Created by george on 29.12.2015.
 */
public class WrongTypeException extends RuntimeException {
    public WrongTypeException() {
    }

    public WrongTypeException(String message) {
        super(message);
    }

    public WrongTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
