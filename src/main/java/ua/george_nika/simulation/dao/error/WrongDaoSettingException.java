package ua.george_nika.simulation.dao.error;

/**
 * Created by george on 06.12.2015.
 */
public class WrongDaoSettingException extends RuntimeException {
    public WrongDaoSettingException() {
    }

    public WrongDaoSettingException(String message) {
        super(message);
    }

    public WrongDaoSettingException(String message, Throwable cause) {
        super(message, cause);
    }
}
