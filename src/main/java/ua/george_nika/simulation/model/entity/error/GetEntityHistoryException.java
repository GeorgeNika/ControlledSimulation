package ua.george_nika.simulation.model.entity.error;

/**
 * Created by george on 08.12.2015.
 */
public class GetEntityHistoryException extends RuntimeException {
    public GetEntityHistoryException() {
    }

    public GetEntityHistoryException(String message) {
        super(message);
    }

    public GetEntityHistoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
