package ua.george_nika.simulation.dao.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class NoResultDaoException extends NoUserFriendlyException {
    public NoResultDaoException() {
        super();
    }

    public NoResultDaoException(String message) {
        super(message);
    }

    public NoResultDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoResultDaoException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
