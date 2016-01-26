package ua.george_nika.simulation.dao.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class WrongSetFieldDaoException extends NoUserFriendlyException {
    public WrongSetFieldDaoException() {
    }

    public WrongSetFieldDaoException(String message) {
        super(message);
    }

    public WrongSetFieldDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongSetFieldDaoException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
