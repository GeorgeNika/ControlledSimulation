package ua.george_nika.simulation.dao.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class WrongDaoSettingException extends NoUserFriendlyException {
    public WrongDaoSettingException() {
    }

    public WrongDaoSettingException(String message) {
        super(message);
    }

    public WrongDaoSettingException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongDaoSettingException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
