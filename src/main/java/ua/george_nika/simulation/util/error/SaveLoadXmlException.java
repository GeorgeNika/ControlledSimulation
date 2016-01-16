package ua.george_nika.simulation.util.error;

/**
 * Created by george on 29.12.2015.
 */
public class SaveLoadXmlException extends RuntimeException {
    public SaveLoadXmlException() {
    }

    public SaveLoadXmlException(String message) {
        super(message);
    }

    public SaveLoadXmlException(String message, Throwable cause) {
        super(message, cause);
    }
}
