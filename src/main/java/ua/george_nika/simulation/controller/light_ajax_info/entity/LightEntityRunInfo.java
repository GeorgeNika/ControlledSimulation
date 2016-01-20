/**
 * used for send request when get ajax response
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.light_ajax_info.entity;

import ua.george_nika.simulation.model.entity.EntityHistory;

@SuppressWarnings("unused")

public class LightEntityRunInfo {
    private int idEntityHistory;
    private int idEntity;
    private String entityType;
    private String infoString;

    public LightEntityRunInfo(EntityHistory entityHistory) {
        this.idEntityHistory = entityHistory.getIdEntityHistory();
        this.idEntity = entityHistory.getIdEntity();
        this.entityType = entityHistory.getEntityType();
        this.infoString = entityHistory.getRunInfoString();
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

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getInfoString() {
        return infoString;
    }

    public void setInfoString(String infoString) {
        this.infoString = infoString;
    }
}
