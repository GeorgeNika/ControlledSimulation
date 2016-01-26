package ua.george_nika.simulation.service.error;

import ua.george_nika.simulation.util.error.UserFriendlyException;

@SuppressWarnings("unused")

public class LoginActionException extends UserFriendlyException {
    public LoginActionException() {
    }

    public LoginActionException(String message) {
        super(message);
    }

    public LoginActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginActionException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
