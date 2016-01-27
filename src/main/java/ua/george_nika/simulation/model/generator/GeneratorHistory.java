/**
 * Interface for model object - generator history
 */

package ua.george_nika.simulation.model.generator;

import ua.george_nika.simulation.model.experiment.ExperimentHistory;

public interface GeneratorHistory {

    void setInitialGeneratorHistoryData(ExperimentHistory experimentHistory, Generator generator);

    void updateGeneratorHistory(Generator generator);

    String getRunInfoString();

    String getLogIdentifyMessage();

    void addEntity(int quantity);

    void destroyEntity(int quantity);


    // getter and setter from abstract class

    int getIdGeneratorHistory();

    void setIdGeneratorHistory(int idGeneratorHistory);

    int getIdGenerator();

    void setIdGenerator(int idGenerator);

    int getIdExperimentHistory();

    void setIdExperimentHistory(int idExperimentHistory);

    int getIdExperiment();

    void setIdExperiment(int idExperiment);

    String getGeneratorName();

    void setGeneratorName(String generatorName);

    String getGeneratorType();

    void setGeneratorType(String generatorType);

    String getLoggerName();

    void setLoggerName(String loggerName);

    int getCreateEntity();

    void setCreateEntity(int createEntity);

    int getDestroyEntity();

    void setDestroyEntity(int destroyEntity);
}
