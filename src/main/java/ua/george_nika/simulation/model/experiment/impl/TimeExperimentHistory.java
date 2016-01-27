/**
 * Special variables and methods for "time" experiment history
 */

package ua.george_nika.simulation.model.experiment.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentHistoryFactory;
import ua.george_nika.simulation.model.experiment.abstr.AbstractExperimentHistory;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.error.NoSuchGenerator;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.List;

@Component
public class TimeExperimentHistory extends AbstractExperimentHistory {

    public static final String EXPERIMENT_TYPE = TimeExperiment.EXPERIMENT_TYPE;

    static {
        ExperimentHistoryFactory.registerClassInFactory(EXPERIMENT_TYPE,
                TimeExperimentHistory.class.getCanonicalName());
    }

    private long minTimeMs;
    private int idStartGenerator;
    private String startGeneratorName;
    private int idEndGenerator;
    private String endGeneratorName;


    @Override
    protected void setInitialExperimentHistoryExtraData(Experiment experiment) {
        TimeExperiment timeExperiment = ClassTypeUtil.getCheckedClass(experiment, TimeExperiment.class);
        this.idStartGenerator = timeExperiment.getIdStartGenerator();
        this.startGeneratorName = getGeneratorNameById(experiment, idStartGenerator);
        this.idEndGenerator = timeExperiment.getIdEndGenerator();
        this.endGeneratorName = getGeneratorNameById(experiment, idEndGenerator);
        this.minTimeMs = Integer.MAX_VALUE;
    }

    @Override
    protected void updateExperimentHistoryExtraData(Experiment experiment) {
        TimeExperiment timeExperiment = ClassTypeUtil.getCheckedClass(experiment, TimeExperiment.class);
        this.minTimeMs = timeExperiment.getMinTime();
    }

    protected String getGeneratorNameById(Experiment experiment, int idGenerator) {
        List<Generator> generatorList = experiment.getGeneratorList();
        for (Generator loopGenerator : generatorList) {
            if (loopGenerator.getIdGenerator() == idGenerator) {
                return loopGenerator.getGeneratorName();
            }
        }
        AppLog.info(experiment.getExperimentHistory().getLoggerName(), "Error. Generator with id - "
                + idGenerator + " don't exist in this experiment");
        throw new NoSuchGenerator("Error. Generator with id - "
                + idGenerator + " don't exist in this experiment");
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

    public String getStartGeneratorName() {
        return startGeneratorName;
    }

    public void setStartGeneratorName(String startGeneratorName) {
        this.startGeneratorName = startGeneratorName;
    }

    public String getEndGeneratorName() {
        return endGeneratorName;
    }

    public void setEndGeneratorName(String endGeneratorName) {
        this.endGeneratorName = endGeneratorName;
    }
}
