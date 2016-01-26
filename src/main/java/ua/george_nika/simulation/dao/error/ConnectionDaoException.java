package ua.george_nika.simulation.dao.error;

import ua.george_nika.simulation.util.error.NoUserFriendlyException;

@SuppressWarnings("unused")

public class ConnectionDaoException extends NoUserFriendlyException {

    public ConnectionDaoException(){
        super();
    }

    public ConnectionDaoException(String message, Throwable cause){
        super(message,cause);
    }

    public ConnectionDaoException(String message){
        super(message);
    }

    public ConnectionDaoException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
