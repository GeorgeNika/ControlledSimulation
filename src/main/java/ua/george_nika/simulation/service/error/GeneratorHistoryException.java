package ua.george_nika.simulation.service.error;

/**
 * Created by george on 04.01.2016.
 */
public class GeneratorHistoryException extends RuntimeException {
    public GeneratorHistoryException() {
    }

    public GeneratorHistoryException(String message) {
        super(message);
    }

    public GeneratorHistoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
