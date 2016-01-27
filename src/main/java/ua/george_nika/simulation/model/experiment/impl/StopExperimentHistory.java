/**
 * Special variables and methods for "stop" experiment history
 */

package ua.george_nika.simulation.model.experiment.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentHistoryFactory;
import ua.george_nika.simulation.model.experiment.abstr.AbstractExperimentHistory;

@Component
public class StopExperimentHistory extends AbstractExperimentHistory {
    public static final String EXPERIMENT_TYPE = StopExperiment.EXPERIMENT_TYPE;

    static {
        ExperimentHistoryFactory.registerClassInFactory(EXPERIMENT_TYPE,
                StopExperimentHistory.class.getCanonicalName());
    }

    @Override
    protected void setInitialExperimentHistoryExtraData(Experiment experiment) {
        // do nothing
    }

    @Override
    protected void updateExperimentHistoryExtraData(Experiment experiment) {
        // do nothing
    }
}
