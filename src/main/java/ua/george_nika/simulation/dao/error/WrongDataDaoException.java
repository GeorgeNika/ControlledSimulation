package ua.george_nika.simulation.dao.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class WrongDataDaoException extends NoUserFriendlyException {
    public WrongDataDaoException() {
    }

    public WrongDataDaoException(String message) {
        super(message);
    }

    public WrongDataDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongDataDaoException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
