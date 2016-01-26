package ua.george_nika.simulation.dao.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class GetDaoException extends NoUserFriendlyException {

    public GetDaoException() {
        super();
    }

    public GetDaoException(String message) {
        super(message);
    }

    public GetDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetDaoException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
