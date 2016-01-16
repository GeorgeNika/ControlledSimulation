package ua.george_nika.simulation.model.generator;

import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;

/**
 * Created by george on 10.01.2016.
 */
public interface GeneratorHistory {

    void setInitialGeneratorHistoryData(ExperimentHistory experimentHistory, Generator generator);

    void updateGeneratorHistory(Generator generator);

    String getRunInfoString();

    String getLogIdentifyMessage();



    public int getIdGeneratorHistory() ;

    public void setIdGeneratorHistory(int idGeneratorHistory) ;

    public int getIdGenerator() ;

    public void setIdGenerator(int idGenerator) ;

    public int getIdExperimentHistory() ;

    public void setIdExperimentHistory(int idExperimentHistory) ;

    public int getIdExperiment() ;

    public void setIdExperiment(int idExperiment) ;

    public String getGeneratorName() ;

    public void setGeneratorName(String generatorName) ;

    public String getGeneratorType() ;

    public void setGeneratorType(String generatorType) ;

    public String getLoggerName() ;

    public void setLoggerName(String loggerName) ;
}
