package ua.george_nika.simulation.service.error;

/**
 * Created by george on 01.01.2016.
 */
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
