package ua.george_nika.simulation.service.error;

/**
 * Created by george on 03.01.2016.
 */
public class RunningExperimentException extends RuntimeException {
    public RunningExperimentException() {
    }

    public RunningExperimentException(String message) {
        super(message);
    }

    public RunningExperimentException(String message, Throwable cause) {
        super(message, cause);
    }
}
