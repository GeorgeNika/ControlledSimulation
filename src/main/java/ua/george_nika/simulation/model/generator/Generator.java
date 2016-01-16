package ua.george_nika.simulation.model.generator;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.model.experiment.Experiment;

import java.util.List;

/**
 * Created by george on 23.11.2015.
 */
public interface Generator {

    void initAction(Experiment experiment);

    void afterInitAction(Experiment experiment);

    void executeMainAction(MutableDateTime currentTime);

    void executeDependentAction(MutableDateTime currentTime);

    void saveGeneratorHistory();

    void updateGeneratorHistory();

    void setNewCurrentTime(MutableDateTime currentTime);

    String getGeneratorType();




    public int getIdGenerator();

    public void setIdGenerator(int idGenerator);

    public String getGeneratorName();

    public void setGeneratorName(String generatorName);

    public String getEntityType();

    public void setEntityType(String entityType);

    public int getIdEntityInfo();

    public void setIdEntityInfo(int idEntity);

    public EntityInfo getEntityInfo();

    public void setEntityInfo(EntityInfo entityInfo);

    public int getEntityQuantity();

    public void setEntityQuantity(int quantityEntity);

    public DateTime getCreated();

    public void setCreated(DateTime created);

    public DateTime getUpdated();

    public void setUpdated(DateTime updated);

    public List<RelatedGeneratorData> getRelatedGeneratorDataList();

    public void setRelatedGeneratorDataList(List<RelatedGeneratorData> relatedGeneratorDataList);

    public List<Entity> getDependentEntityList();

    public void setDependentEntityList(List<Entity> dependentEntityList);

    public DateTime getExperimentStartTime();

    public void setExperimentStartTime(DateTime experimentStartTime);

    public MutableDateTime getCurrentTime();

    public void setCurrentTime(MutableDateTime currentTime);

    public GeneratorHistory getGeneratorHistory();

    public void setGeneratorHistory(GeneratorHistory generatorHistory);

}
