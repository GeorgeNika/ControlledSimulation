package ua.george_nika.simulation.model.entity.error;

/**
 * Created by george on 28.11.2015.
 */
public class WrongTypeInfo extends RuntimeException {

    public WrongTypeInfo() {
    }

    public WrongTypeInfo(String message) {
        super(message);
    }

    public WrongTypeInfo(String message, Throwable cause) {
        super(message, cause);
    }
}
