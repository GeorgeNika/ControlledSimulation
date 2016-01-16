package ua.george_nika.simulation.util.error;

/**
 * Created by george on 03.01.2016.
 */
public class NoEmptyLoggerInPool extends RuntimeException {
    public NoEmptyLoggerInPool() {
    }

    public NoEmptyLoggerInPool(String message) {
        super(message);
    }

    public NoEmptyLoggerInPool(String message, Throwable cause) {
        super(message, cause);
    }
}
