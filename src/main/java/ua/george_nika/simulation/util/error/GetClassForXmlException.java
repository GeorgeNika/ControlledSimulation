package ua.george_nika.simulation.util.error;

@SuppressWarnings("unused")

public class GetClassForXmlException extends NoUserFriendlyException {
    public GetClassForXmlException() {
    }

    public GetClassForXmlException(String message) {
        super(message);
    }

    public GetClassForXmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetClassForXmlException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
