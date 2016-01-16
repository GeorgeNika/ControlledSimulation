package ua.george_nika.simulation.service.error;

/**
 * Created by george on 01.01.2016.
 */
public class NoExperimentWithHistory extends RuntimeException {
    public NoExperimentWithHistory() {
    }

    public NoExperimentWithHistory(String message) {
        super(message);
    }

    public NoExperimentWithHistory(String message, Throwable cause) {
        super(message, cause);
    }
}
