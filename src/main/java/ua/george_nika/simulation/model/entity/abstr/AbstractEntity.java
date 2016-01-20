package ua.george_nika.simulation.model.entity.abstr;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.entity.EntityHistory;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.RelatedGeneratorData;
import ua.george_nika.simulation.service.entity.EntityHistoryService;
import ua.george_nika.simulation.util.AppLog;

import java.util.List;

/**
 * Created by george on 24.11.2015.
 */

abstract public class AbstractEntity implements Entity {

    protected int idEntity;
    protected DateTime creationTime;
    protected boolean needRemove = false;

    protected List<RelatedGeneratorData> relationGeneratorDataList;
    protected int nextRelationPoint = 0;
    protected MutableDateTime nextRelationTime = new MutableDateTime();

    protected EntityHistory entityHistory;


    protected MutableDateTime currentTime = new MutableDateTime();

    abstract protected void setEntityInfo(EntityInfo entityInfo);

    @Override
    public void initEntityAction(Generator generator, Object specialInfo, DateTime creationTime) {
        this.creationTime = generator.getExperimentStartTime();
        this.relationGeneratorDataList = generator.getRelatedGeneratorDataList();
        setEntityInfo(generator.getEntityInfo());

        entityHistory = EntityHistoryService.getNewEntityHistory(generator, this);
        AppLog.info(entityHistory.getLoggerName(), entityHistory.getLogIdentifyMessage()
                + " init entity " + getEntityType() + " : " + "for generator " + generator.getGeneratorType() + " : "
                + generator.getIdGenerator() + " - " + generator.getGeneratorName()
                + " "+generator.getGeneratorHistory().getLogIdentifyMessage()
                + " with history id - " + entityHistory.getIdEntityHistory());
    }

    @Override
    public void saveEntityHistory() {
        EntityHistoryService.saveEntityHistory(entityHistory);
    }

    @Override
    public void updateEntityHistory() {
        entityHistory.updateEntityHistory(this);
    }

    public RelatedGeneratorData getNextRelationGeneratorData() {
        return relationGeneratorDataList.get(nextRelationPoint);
    }

    public boolean isNeedRemove() {
        if (needRemove) {
            return true;
        } else {
            return false;
        }
    }

    public void setNewCurrentTime(MutableDateTime newCurrentTime) {
        this.currentTime.setMillis(newCurrentTime);
    }

    public EntityHistory getEntityHistory() {
        return entityHistory;
    }

    public void setEntityHistory(EntityHistory entityHistory) {
        this.entityHistory = entityHistory;
    }

    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
    }
}
