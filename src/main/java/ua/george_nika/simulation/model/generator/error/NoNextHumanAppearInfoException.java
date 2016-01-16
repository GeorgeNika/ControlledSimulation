package ua.george_nika.simulation.model.generator.error;

/**
 * Created by george on 26.11.2015.
 */
public class NoNextHumanAppearInfoException extends RuntimeException{

    public NoNextHumanAppearInfoException() {
    }

    public NoNextHumanAppearInfoException(String message) {
        super(message);
    }

    public NoNextHumanAppearInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}
