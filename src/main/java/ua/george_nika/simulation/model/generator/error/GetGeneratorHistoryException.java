package ua.george_nika.simulation.model.generator.error;

/**
 * Created by george on 04.12.2015.
 */
public class GetGeneratorHistoryException extends RuntimeException{

    public GetGeneratorHistoryException() {
        super();
    }

    public GetGeneratorHistoryException(String message) {
        super(message);
    }

    public GetGeneratorHistoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
