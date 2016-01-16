package ua.george_nika.simulation.controller.error;

/**
 * Created by george on 29.12.2015.
 */
public class WrongTypeController extends RuntimeException{
    public WrongTypeController() {
    }

    public WrongTypeController(String message) {
        super(message);
    }

    public WrongTypeController(String message, Throwable cause) {
        super(message, cause);
    }
}
