/**
 * Interface for model object - entity history
 */

package ua.george_nika.simulation.model.entity;

import org.joda.time.DateTime;
import ua.george_nika.simulation.model.generator.Generator;

public interface EntityHistory {

    void setInitialEntityHistoryData(Generator generator, Entity entity);

    void updateEntityHistory(Entity entity);

    String getRunInfoString();

    String getLogIdentifyMessage();


    // getter and setter from abstract class

    int getIdEntityHistory();

    void setIdEntityHistory(int idEntityHistory);

    int getIdEntity();

    void setIdEntity(int idEntity);

    int getIdGenerator();

    void setIdGenerator(int idGenerator);

    int getIdGeneratorHistory();

    void setIdGeneratorHistory(int idGeneratorHistory);

    int getIdExperiment();

    void setIdExperiment(int idExperiment);

    int getIdExperimentHistory();

    void setIdExperimentHistory(int idExperimentHistory);

    DateTime getCreateTime();

    void setCreateTime(DateTime createTime);

    String getEntityType();

    void setEntityType(String entityType);

    String getLoggerName();

    void setLoggerName(String loggerName);
}
