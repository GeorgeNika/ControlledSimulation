package ua.george_nika.simulation.service.error;

/**
 * Created by george on 29.12.2015.
 */
public class WrongIdException extends RuntimeException {
    public WrongIdException() {
    }

    public WrongIdException(String message) {
        super(message);
    }

    public WrongIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
