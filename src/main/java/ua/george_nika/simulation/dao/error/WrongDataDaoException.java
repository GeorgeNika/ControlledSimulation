package ua.george_nika.simulation.dao.error;

/**
 * Created by george on 06.12.2015.
 */
public class WrongDataDaoException extends RuntimeException {
    public WrongDataDaoException() {
    }

    public WrongDataDaoException(String message) {
        super(message);
    }

    public WrongDataDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
