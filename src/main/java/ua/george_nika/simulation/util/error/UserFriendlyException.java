package ua.george_nika.simulation.util.error;

import ua.george_nika.simulation.util.AppLog;

@SuppressWarnings("unused")

public class UserFriendlyException extends RuntimeException {
    public UserFriendlyException() {
    }

    public UserFriendlyException(String message) {
        super(message);
    }

    public UserFriendlyException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserFriendlyException(String loggerName, String className, String message, Throwable cause){
        super(cause.getMessage() + ". Because " + message) ;
        AppLog.error(loggerName, className, message, cause);
    }
}
