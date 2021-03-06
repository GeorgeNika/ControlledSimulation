/**
 * Special variables and methods for "simple" experiment history
 */

package ua.george_nika.simulation.model.experiment.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.experiment.abstr.AbstractExperimentHistory;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentHistoryFactory;

@Component
public class SimpleExperimentHistory extends AbstractExperimentHistory {
    public static final String EXPERIMENT_TYPE = SimpleExperiment.EXPERIMENT_TYPE;

    static {
        ExperimentHistoryFactory.registerClassInFactory(EXPERIMENT_TYPE,
                SimpleExperimentHistory.class.getCanonicalName());
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
