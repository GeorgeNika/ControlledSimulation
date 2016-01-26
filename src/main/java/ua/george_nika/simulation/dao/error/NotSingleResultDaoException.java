package ua.george_nika.simulation.dao.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class NotSingleResultDaoException extends NoUserFriendlyException{

    public NotSingleResultDaoException() {
    }

    public NotSingleResultDaoException(String message) {
        super(message);
    }

    public NotSingleResultDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSingleResultDaoException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
