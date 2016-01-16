package ua.george_nika.simulation.dao.error;

/**
 * Created by george on 02.12.2015.
 */
public class ConnectionDaoException extends RuntimeException {

    public ConnectionDaoException(){
        super();
    }

    public ConnectionDaoException(String message, Throwable cause){
        super(message,cause);
    }

    public ConnectionDaoException(String message){
        super(message);
    }

}
