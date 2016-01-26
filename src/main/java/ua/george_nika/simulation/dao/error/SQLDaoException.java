package ua.george_nika.simulation.dao.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class SQLDaoException extends NoUserFriendlyException {
    public SQLDaoException() {
    }

    public SQLDaoException(String message) {
        super(message);
    }

    public SQLDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public SQLDaoException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
