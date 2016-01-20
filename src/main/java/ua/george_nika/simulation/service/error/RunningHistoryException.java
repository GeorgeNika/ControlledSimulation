package ua.george_nika.simulation.service.error;

/**
 * Created by george on 03.01.2016.
 */
public class RunningHistoryException extends RuntimeException {
    public RunningHistoryException() {
    }

    public RunningHistoryException(String message) {
        super(message);
    }

    public RunningHistoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
