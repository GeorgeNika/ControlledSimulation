package ua.george_nika.simulation.util.error;

import ua.george_nika.simulation.util.AppLog;

@SuppressWarnings("unused")

public class NoUserFriendlyException extends RuntimeException {
    public NoUserFriendlyException() {
    }

    public NoUserFriendlyException(String message) {
        super(message);
    }

    public NoUserFriendlyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoUserFriendlyException(String loggerName, String className, String message, Throwable cause){
        super(message, cause) ;
        AppLog.error(loggerName, className, message, cause);
    }
}
