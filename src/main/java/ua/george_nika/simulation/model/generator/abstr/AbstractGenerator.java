/**
 * Common variables and methods for generator
 */

package ua.george_nika.simulation.model.generator.abstr;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.GeneratorHistory;
import ua.george_nika.simulation.model.generator.RelatedGeneratorData;
import ua.george_nika.simulation.model.generator.error.NoSuchGenerator;
import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.service.generator.GeneratorHistoryService;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.DateTimeXmlAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
abstract public class AbstractGenerator implements Generator {

    protected int idGenerator;
    protected String generatorName;

    protected String entityType;
    protected int idEntityInfo;

    @XmlAnyElement(lax = true)
    protected EntityInfo entityInfo;
    protected int entityQuantity;
    @XmlJavaTypeAdapter(DateTimeXmlAdapter.class)
    protected DateTime created;
    @XmlJavaTypeAdapter(DateTimeXmlAdapter.class)
    protected DateTime updated;

    @XmlElementWrapper
    @XmlAnyElement(lax = true)
    protected List<RelatedGeneratorData> relatedGeneratorDataList = new ArrayList<>();

    @XmlTransient
    protected List<Entity> dependentEntityList = new LinkedList<>();
    @XmlTransient
    protected DateTime experimentStartTime;
    @XmlTransient
    protected MutableDateTime currentTime = new MutableDateTime();
    @XmlTransient
    protected GeneratorHistory generatorHistory;

    public void initAction(Experiment experiment) {
        dependentEntityList = new LinkedList<>();
        experimentStartTime = experiment.getStartTime();
        currentTime = experimentStartTime.toMutableDateTime();
        generatorHistory = GeneratorHistoryService.getNewGeneratorHistory(experiment, this);
        logAboutInitAction();
    }

    public void afterInitAction(Experiment experiment) {
        logAboutAfterInitAction();
    }

    protected void logAboutInitAction() {
        AppLog.info(generatorHistory.getLoggerName(), generatorHistory.getLogIdentifyMessage()
                + "init generator " + getGeneratorType() + " : " + idGenerator + " - " + generatorName
                + " with history id - " + generatorHistory.getIdGeneratorHistory());
    }

    protected void logAboutAfterInitAction() {
        AppLog.info(generatorHistory.getLoggerName(), generatorHistory.getLogIdentifyMessage()
                + "after init generator");
    }

    @Override
    public void saveGeneratorHistory() {
        GeneratorHistoryService.saveGeneratorHistory(generatorHistory);
        for (Entity loopEntity : dependentEntityList) {
            loopEntity.saveEntityHistory();
        }
    }

    @Override
    public void updateGeneratorHistory() {
        for (Entity loopEntity : dependentEntityList) {
            loopEntity.updateEntityHistory();
        }
        generatorHistory.updateGeneratorHistory(this);
    }

    public void setNewCurrentTime(MutableDateTime newCurrentTime) {
        this.currentTime.setMillis(newCurrentTime);
        for (Entity loopEntity : dependentEntityList) {
            loopEntity.setNewCurrentTime(newCurrentTime);
        }
    }

    protected Generator getGeneratorById(List<Generator> generatorList, int idGenerator) {
        for (Generator loopGenerator : generatorList) {
            if (loopGenerator.getIdGenerator() == idGenerator) {
                return loopGenerator;
            }
        }
        AppLog.info(generatorHistory.getLoggerName(), "Error. Generator with id - " + idGenerator +
                " don't exist in this experiment");
        throw new NoSuchGenerator("Error. Generator with id - " + idGenerator + " don't exist in this experiment");
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

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public int getIdEntityInfo() {
        return idEntityInfo;
    }

    public void setIdEntityInfo(int idEntityInfo) {
        this.idEntityInfo = idEntityInfo;
    }

    public EntityInfo getEntityInfo() {
        return entityInfo;
    }

    public void setEntityInfo(EntityInfo entityInfo) {
        this.entityInfo = entityInfo;
    }

    public int getEntityQuantity() {
        return entityQuantity;
    }

    public void setEntityQuantity(int entityQuantity) {
        this.entityQuantity = entityQuantity;
    }

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public DateTime getUpdated() {
        return updated;
    }

    public void setUpdated(DateTime updated) {
        this.updated = updated;
    }

    public List<RelatedGeneratorData> getRelatedGeneratorDataList() {
        return relatedGeneratorDataList;
    }

    public void setRelatedGeneratorDataList(List<RelatedGeneratorData> relatedGeneratorDataList) {
        this.relatedGeneratorDataList = relatedGeneratorDataList;
    }

    public List<Entity> getDependentEntityList() {
        return dependentEntityList;
    }

    public void setDependentEntityList(List<Entity> dependentEntityList) {
        this.dependentEntityList = dependentEntityList;
    }

    public DateTime getExperimentStartTime() {
        return experimentStartTime;
    }

    public void setExperimentStartTime(DateTime experimentStartTime) {
        this.experimentStartTime = experimentStartTime;
    }

    public MutableDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(MutableDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public GeneratorHistory getGeneratorHistory() {
        return generatorHistory;
    }

    public void setGeneratorHistory(GeneratorHistory generatorHistory) {
        this.generatorHistory = generatorHistory;
    }
}
