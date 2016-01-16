package ua.george_nika.simulation.model.experiment.error;

/**
 * Created by george on 04.12.2015.
 */
public class GetExperimentHistoryException extends RuntimeException{

    public GetExperimentHistoryException() {
        super();
    }

    public GetExperimentHistoryException(String message) {
        super(message);
    }

    public GetExperimentHistoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
