package ua.george_nika.simulation.service.error;

@SuppressWarnings("unused")

public class NoSuchHistory extends RuntimeException {
    public NoSuchHistory() {
    }

    public NoSuchHistory(String message) {
        super(message);
    }

    public NoSuchHistory(String message, Throwable cause) {
        super(message, cause);
    }
}
