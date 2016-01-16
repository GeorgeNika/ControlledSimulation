package ua.george_nika.simulation.service.error;

/**
 * Created by george on 04.01.2016.
 */
public class ExperimentHistoryException extends RuntimeException {
    public ExperimentHistoryException() {
    }

    public ExperimentHistoryException(String message) {
        super(message);
    }

    public ExperimentHistoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
