package ua.george_nika.simulation.model.generator.error;

/**
 * Created by george on 26.11.2015.
 */
public class NoSuchGenerator extends RuntimeException {
    public NoSuchGenerator() {
    }

    public NoSuchGenerator(String message) {
        super(message);
    }

    public NoSuchGenerator(String message, Throwable cause) {
        super(message, cause);
    }
}
