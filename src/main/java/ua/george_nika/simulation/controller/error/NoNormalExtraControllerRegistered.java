package ua.george_nika.simulation.controller.error;

@SuppressWarnings("unused")

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
