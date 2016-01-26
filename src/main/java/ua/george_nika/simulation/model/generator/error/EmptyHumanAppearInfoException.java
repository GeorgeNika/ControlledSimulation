package ua.george_nika.simulation.model.generator.error;

import ua.george_nika.simulation.util.error.UserFriendlyException;

/**
 * Created by george on 26.11.2015.
 */
public class EmptyHumanAppearInfoException extends UserFriendlyException {

    public EmptyHumanAppearInfoException() {
    }

    public EmptyHumanAppearInfoException(String message) {
        super(message);
    }

    public EmptyHumanAppearInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}
