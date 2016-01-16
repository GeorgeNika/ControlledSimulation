package ua.george_nika.simulation.model.entity.error;

/**
 * Created by george on 28.11.2015.
 */
public class StationWithoutRoute extends RuntimeException{
    public StationWithoutRoute() {
    }

    public StationWithoutRoute(String message) {
        super(message);
    }

    public StationWithoutRoute(String message, Throwable cause) {
        super(message, cause);
    }
}
