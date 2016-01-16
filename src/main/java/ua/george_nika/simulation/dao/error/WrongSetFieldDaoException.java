package ua.george_nika.simulation.dao.error;

/**
 * Created by george on 06.12.2015.
 */
public class WrongSetFieldDaoException extends RuntimeException {
    public WrongSetFieldDaoException() {
    }

    public WrongSetFieldDaoException(String message) {
        super(message);
    }

    public WrongSetFieldDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
