/**
 * used for send request when get ajax response
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.light_ajax_info.entity;

import ua.george_nika.simulation.model.entity.EntityHistory;
import ua.george_nika.simulation.util.TimeToStringUtil;

public class LightEntityHistory {

    protected int idEntityHistory;
    protected int idEntity;
    protected int idGeneratorHistory;
    protected int idGenerator;
    protected int idExperimentHistory;
    protected int idExperiment;
    protected String entityType;
    protected String createTime;

    public LightEntityHistory(EntityHistory entityHistory) {
        this.idEntityHistory = entityHistory.getIdEntityHistory();
        this.idEntity = entityHistory.getIdEntity();
        this.idGeneratorHistory = entityHistory.getIdGeneratorHistory();
        this.idGenerator = entityHistory.getIdGenerator();
        this.idExperimentHistory = entityHistory.getIdExperimentHistory();
        this.idExperiment = entityHistory.getIdExperiment();
        this.entityType = entityHistory.getEntityType();
        this.createTime = TimeToStringUtil.getSafeString(entityHistory.getCreateTime());
    }

    public int getIdEntityHistory() {
        return idEntityHistory;
    }

    public void setIdEntityHistory(int idEntityHistory) {
        this.idEntityHistory = idEntityHistory;
    }

    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
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

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
