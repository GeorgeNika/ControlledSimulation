package ua.george_nika.simulation.dao.error;

/**
 * Created by george on 06.12.2015.
 */
public class SQLDaoException extends RuntimeException {
    public SQLDaoException() {
    }

    public SQLDaoException(String message) {
        super(message);
    }

    public SQLDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
