package ua.george_nika.simulation.model.experiment.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentHistoryFactory;
import ua.george_nika.simulation.model.experiment.abstr.AbstractExperimentHistory;
import ua.george_nika.simulation.util.ClassTypeUtil;

/**
 * Created by george on 15.01.2016.
 */
@Component
public class TimeExperimentHistory extends AbstractExperimentHistory{

    public static final String EXPERIMENT_TYPE = TimeExperiment.EXPERIMENT_TYPE;

    static {
        ExperimentHistoryFactory.registerClassInFactory(EXPERIMENT_TYPE,
                TimeExperimentHistory.class.getCanonicalName());
    }

    private long minTimeMs;
    private int idStartGenerator;
    private int idEndGenerator;

    @Override
    protected void setInitialExperimentHistoryExtraData(Experiment experiment) {
        TimeExperiment timeExperiment = ClassTypeUtil.getCheckedClass(experiment, TimeExperiment.class);
        this.idStartGenerator = timeExperiment.getIdStartGenerator();
        this.idEndGenerator = timeExperiment.getIdEndGenerator();
        this.minTimeMs = Integer.MAX_VALUE;
    }

    @Override
    protected void updateExperimentHistoryExtraData(Experiment experiment) {
        TimeExperiment timeExperiment = ClassTypeUtil.getCheckedClass(experiment, TimeExperiment.class);
        this.minTimeMs = timeExperiment.getMinTime();
        // todo this.time = timeExperiment.
    }

    public long getMinTimeMs() {
        return minTimeMs;
    }

    public void setMinTimeMs(long minTimeMs) {
        this.minTimeMs = minTimeMs;
    }

    public int getIdStartGenerator() {
        return idStartGenerator;
    }

    public void setIdStartGenerator(int idStartGenerator) {
        this.idStartGenerator = idStartGenerator;
    }

    public int getIdEndGenerator() {
        return idEndGenerator;
    }

    public void setIdEndGenerator(int idEndGenerator) {
        this.idEndGenerator = idEndGenerator;
    }
}
