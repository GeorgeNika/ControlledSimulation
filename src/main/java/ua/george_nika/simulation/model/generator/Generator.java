/**
 * Interface for model object - generator
 */

package ua.george_nika.simulation.model.generator;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.model.experiment.Experiment;

import java.util.List;

@SuppressWarnings("unused")

public interface Generator {

    void initAction(Experiment experiment);

    void afterInitAction(Experiment experiment);

    void executeMainAction(MutableDateTime currentTime);

    void executeDependentAction(MutableDateTime currentTime);

    void saveGeneratorHistory();

    void updateGeneratorHistory();

    void setNewCurrentTime(MutableDateTime currentTime);

    String getGeneratorType();

    List<Entity> sendEntityListToEntity(Entity recipientEntity);

    void receiveEntityListFromEntity(List<Entity> entityList, Entity donorEntity);


    // getter and setter from abstract class

    int getIdGenerator();

    void setIdGenerator(int idGenerator);

    String getGeneratorName();

    void setGeneratorName(String generatorName);

    String getEntityType();

    void setEntityType(String entityType);

    int getIdEntityInfo();

    void setIdEntityInfo(int idEntity);

    EntityInfo getEntityInfo();

    void setEntityInfo(EntityInfo entityInfo);

    int getEntityQuantity();

    void setEntityQuantity(int quantityEntity);

    DateTime getCreated();

    void setCreated(DateTime created);

    DateTime getUpdated();

    void setUpdated(DateTime updated);

    List<RelatedGeneratorData> getRelatedGeneratorDataList();

    void setRelatedGeneratorDataList(List<RelatedGeneratorData> relatedGeneratorDataList);

    List<Entity> getDependentEntityList();

    void setDependentEntityList(List<Entity> dependentEntityList);

    DateTime getExperimentStartTime();

    void setExperimentStartTime(DateTime experimentStartTime);

    MutableDateTime getCurrentTime();

    void setCurrentTime(MutableDateTime currentTime);

    GeneratorHistory getGeneratorHistory();

    void setGeneratorHistory(GeneratorHistory generatorHistory);

}
