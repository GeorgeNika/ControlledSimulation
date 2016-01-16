package ua.george_nika.simulation.dao.error;

/**
 * Created by george on 06.12.2015.
 */
public class UnknownTypeOfFieldDaoException extends RuntimeException {
    public UnknownTypeOfFieldDaoException() {
    }

    public UnknownTypeOfFieldDaoException(String message) {
        super(message);
    }

    public UnknownTypeOfFieldDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
