package ua.george_nika.simulation.controller.error;

@SuppressWarnings("unused")

public class DownloadFileException extends RuntimeException{
    public DownloadFileException() {
    }

    public DownloadFileException(String message) {
        super(message);
    }

    public DownloadFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
