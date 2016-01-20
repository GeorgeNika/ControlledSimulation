/**
 * used for send request when get ajax response
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.light_ajax_info.entity;

import ua.george_nika.simulation.model.entity.EntityInfo;

@SuppressWarnings("unused")

public class LightEntityInfo {

    private int idEntityInfo;
    private String fullInfo;

    public LightEntityInfo(EntityInfo entityInfo) {
        this.idEntityInfo = entityInfo.getIdEntityInfo();
        this.fullInfo = entityInfo.getInfoString();
    }

    public int getIdEntityInfo() {
        return idEntityInfo;
    }

    public void setIdEntityInfo(int idEntityInfo) {
        this.idEntityInfo = idEntityInfo;
    }

    public String getFullInfo() {
        return fullInfo;
    }

    public void setFullInfo(String fullInfo) {
        this.fullInfo = fullInfo;
    }
}
