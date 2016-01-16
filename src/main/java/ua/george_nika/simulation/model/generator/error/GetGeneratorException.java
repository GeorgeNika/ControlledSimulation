package ua.george_nika.simulation.model.generator.error;

/**
 * Created by george on 08.12.2015.
 */
public class GetGeneratorException extends RuntimeException {
    public GetGeneratorException() {
    }

    public GetGeneratorException(String message) {
        super(message);
    }

    public GetGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }
}
