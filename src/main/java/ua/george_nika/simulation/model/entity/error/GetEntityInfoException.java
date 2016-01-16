package ua.george_nika.simulation.model.entity.error;

/**
 * Created by george on 08.12.2015.
 */
public class GetEntityInfoException extends RuntimeException {
    public GetEntityInfoException() {
    }

    public GetEntityInfoException(String message) {
        super(message);
    }

    public GetEntityInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}
