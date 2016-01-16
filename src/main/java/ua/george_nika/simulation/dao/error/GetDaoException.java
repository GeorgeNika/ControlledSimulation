package ua.george_nika.simulation.dao.error;

/**
 * Created by george on 04.12.2015.
 */
public class GetDaoException extends RuntimeException{

    public GetDaoException() {
        super();
    }

    public GetDaoException(String message) {
        super(message);
    }

    public GetDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
