package ua.george_nika.simulation.util.error;

/**
 * Created by george on 03.01.2016.
 */
public class LoggerPoolWrongName extends RuntimeException {
    public LoggerPoolWrongName() {
    }

    public LoggerPoolWrongName(String message) {
        super(message);
    }

    public LoggerPoolWrongName(String message, Throwable cause) {
        super(message, cause);
    }
}
