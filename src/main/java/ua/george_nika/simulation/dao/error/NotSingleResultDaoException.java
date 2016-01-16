package ua.george_nika.simulation.dao.error;

/**
 * Created by george on 06.12.2015.
 */
public class NotSingleResultDaoException extends RuntimeException{

    public NotSingleResultDaoException() {
    }

    public NotSingleResultDaoException(String message) {
        super(message);
    }

    public NotSingleResultDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
