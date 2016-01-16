package ua.george_nika.simulation.service.error;

/**
 * Created by george on 04.01.2016.
 */
public class GeneratorException extends RuntimeException {
    public GeneratorException() {
    }

    public GeneratorException(String message) {
        super(message);
    }

    public GeneratorException(String message, Throwable cause) {
        super(message, cause);
    }
}
