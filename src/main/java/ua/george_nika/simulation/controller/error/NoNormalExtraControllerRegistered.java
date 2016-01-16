package ua.george_nika.simulation.controller.error;

/**
 * Created by george on 16.12.2015.
 */
public class NoNormalExtraControllerRegistered extends RuntimeException {
    public NoNormalExtraControllerRegistered() {
    }

    public NoNormalExtraControllerRegistered(String message) {
        super(message);
    }

    public NoNormalExtraControllerRegistered(String message, Throwable cause) {
        super(message, cause);
    }
}
