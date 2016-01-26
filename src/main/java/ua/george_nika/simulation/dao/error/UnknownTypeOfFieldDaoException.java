package ua.george_nika.simulation.dao.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class UnknownTypeOfFieldDaoException extends NoUserFriendlyException {
    public UnknownTypeOfFieldDaoException() {
    }

    public UnknownTypeOfFieldDaoException(String message) {
        super(message);
    }

    public UnknownTypeOfFieldDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownTypeOfFieldDaoException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
