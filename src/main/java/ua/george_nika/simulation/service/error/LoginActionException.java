package ua.george_nika.simulation.service.error;

/**
 * Created by george on 04.01.2016.
 */
public class LoginActionException extends RuntimeException {
    public LoginActionException() {
    }

    public LoginActionException(String message) {
        super(message);
    }

    public LoginActionException(String message, Throwable cause) {
        super(message, cause);
    }
}
