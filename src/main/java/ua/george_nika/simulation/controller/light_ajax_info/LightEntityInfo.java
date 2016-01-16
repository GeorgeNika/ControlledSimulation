package ua.george_nika.simulation.controller.light_ajax_info;

import ua.george_nika.simulation.model.entity.EntityInfo;

/**
 * Created by george on 18.12.2015.
 */
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
