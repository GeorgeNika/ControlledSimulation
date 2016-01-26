package ua.george_nika.simulation.util.error;

@SuppressWarnings("unused")

public class SaveLoadXmlException extends UserFriendlyException {
    public SaveLoadXmlException() {
    }

    public SaveLoadXmlException(String message) {
        super(message);
    }

    public SaveLoadXmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveLoadXmlException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
