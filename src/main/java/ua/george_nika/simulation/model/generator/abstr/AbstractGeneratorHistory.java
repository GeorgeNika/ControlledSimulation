package ua.george_nika.simulation.model.generator.abstr;

import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.GeneratorHistory;

/**
 * Created by george on 10.01.2016.
 */
abstract public class AbstractGeneratorHistory implements GeneratorHistory {

    protected int idExperimentHistory;
    protected int idExperiment;
    protected int idGeneratorHistory;
    protected int idGenerator;
    protected String generatorName;
    protected String generatorType;
    protected String loggerName;

    abstract protected void setInitialGeneratorHistoryExtraData(Generator generator);

    abstract protected void updateGeneratorHistoryExtraData(Generator generator);

    @Override
    public void setInitialGeneratorHistoryData(ExperimentHistory experimentHistory, Generator generator) {
        this.idExperimentHistory = experimentHistory.getIdExperimentHistory();
        this.idExperiment = experimentHistory.getIdExperiment();
        this.idGenerator = generator.getIdGenerator();
        this.generatorName = generator.getGeneratorName();
        this.generatorType = generator.getGeneratorType();
        this.loggerName = experimentHistory.getLoggerName();

        setInitialGeneratorHistoryExtraData(generator);
    }

    @Override
    public void updateGeneratorHistory(Generator generator) {

        updateGeneratorHistoryExtraData(generator);
    }

    @Override
    public String getLogIdentifyMessage() {
        return "[gen hist : " + idGeneratorHistory + " ] - ";
    }

    public int getIdGeneratorHistory() {
        return idGeneratorHistory;
    }

    public void setIdGeneratorHistory(int idGeneratorHistory) {
        this.idGeneratorHistory = idGeneratorHistory;
    }

    public int getIdGenerator() {
        return idGenerator;
    }

    public void setIdGenerator(int idGenerator) {
        this.idGenerator = idGenerator;
    }

    public String getGeneratorName() {
        return generatorName;
    }

    public void setGeneratorName(String generatorName) {
        this.generatorName = generatorName;
    }

    public String getGeneratorType() {
        return generatorType;
    }

    public void setGeneratorType(String generatorType) {
        this.generatorType = generatorType;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public int getIdExperimentHistory() {
        return idExperimentHistory;
    }

    public void setIdExperimentHistory(int idExperimentHistory) {
        this.idExperimentHistory = idExperimentHistory;
    }

    public int getIdExperiment() {
        return idExperiment;
    }

    public void setIdExperiment(int idExperiment) {
        this.idExperiment = idExperiment;
    }
}