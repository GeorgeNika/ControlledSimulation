package ua.george_nika.simulation.dao.error;

/**
 * Created by george on 29.12.2015.
 */
public class NoResultDaoException extends RuntimeException {
    public NoResultDaoException() {
        super();
    }

    public NoResultDaoException(String message) {
        super(message);
    }

    public NoResultDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
