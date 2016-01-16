package ua.george_nika.simulation.service.error;

/**
 * Created by george on 04.01.2016.
 */
public class ExperimentException extends RuntimeException {
    public ExperimentException() {
    }

    public ExperimentException(String message) {
        super(message);
    }

    public ExperimentException(String message, Throwable cause) {
        super(message, cause);
    }
}
