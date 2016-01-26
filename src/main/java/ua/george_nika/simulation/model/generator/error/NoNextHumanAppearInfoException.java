package ua.george_nika.simulation.model.generator.error;

import ua.george_nika.simulation.util.error.UserFriendlyException;

/**
 * Created by george on 26.11.2015.
 */
public class NoNextHumanAppearInfoException extends UserFriendlyException{

    public NoNextHumanAppearInfoException() {
    }

    public NoNextHumanAppearInfoException(String message) {
        super(message);
    }

    public NoNextHumanAppearInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}
