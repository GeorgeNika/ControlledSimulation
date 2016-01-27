/**
 * Special variables and methods for "repeat" experiment history
 */

package ua.george_nika.simulation.model.experiment.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.experiment.abstr.AbstractExperimentHistory;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentHistoryFactory;

@Component
public class RepeatExperimentHistory extends AbstractExperimentHistory {

    public static final String EXPERIMENT_TYPE = RepeatExperiment.EXPERIMENT_TYPE;

    static {
        ExperimentHistoryFactory.registerClassInFactory(EXPERIMENT_TYPE,
                RepeatExperimentHistory.class.getCanonicalName());
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
