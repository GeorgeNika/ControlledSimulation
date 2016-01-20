package ua.george_nika.simulation.controller.error;

@SuppressWarnings("unused")

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
