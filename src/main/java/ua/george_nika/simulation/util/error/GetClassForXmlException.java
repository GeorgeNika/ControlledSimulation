package ua.george_nika.simulation.util.error;

/**
 * Created by george on 29.12.2015.
 */
public class GetClassForXmlException extends RuntimeException {
    public GetClassForXmlException() {
    }

    public GetClassForXmlException(String message) {
        super(message);
    }

    public GetClassForXmlException(String message, Throwable cause) {
        super(message, cause);
    }
}
