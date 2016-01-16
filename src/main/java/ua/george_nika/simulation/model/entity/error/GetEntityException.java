package ua.george_nika.simulation.model.entity.error;

/**
 * Created by george on 08.12.2015.
 */
public class GetEntityException extends RuntimeException {
    public GetEntityException() {
    }

    public GetEntityException(String message) {
        super(message);
    }

    public GetEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
