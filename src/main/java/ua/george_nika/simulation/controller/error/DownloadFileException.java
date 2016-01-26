package ua.george_nika.simulation.controller.error;

import ua.george_nika.simulation.util.error.UserFriendlyException;

@SuppressWarnings("unused")

public class DownloadFileException extends UserFriendlyException{
    public DownloadFileException() {
    }

    public DownloadFileException(String message) {
        super(message);
    }

    public DownloadFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public DownloadFileException(String loggerName, String className, String message, Throwable cause) {
        super(loggerName, className, message, cause);
    }
}
