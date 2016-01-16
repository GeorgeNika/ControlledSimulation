package ua.george_nika.simulation.model.experiment.error;

/**
 * Created by george on 04.12.2015.
 */
public class GetExperimentException extends RuntimeException{

    public GetExperimentException() {
        super();
    }

    public GetExperimentException(String message) {
        super(message);
    }

    public GetExperimentException(String message, Throwable cause) {
        super(message, cause);
    }
}
