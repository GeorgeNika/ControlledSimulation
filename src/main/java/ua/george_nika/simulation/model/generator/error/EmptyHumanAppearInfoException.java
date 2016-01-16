package ua.george_nika.simulation.model.generator.error;

/**
 * Created by george on 26.11.2015.
 */
public class EmptyHumanAppearInfoException extends RuntimeException {

    public EmptyHumanAppearInfoException() {
    }

    public EmptyHumanAppearInfoException(String message) {
        super(message);
    }

    public EmptyHumanAppearInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}
