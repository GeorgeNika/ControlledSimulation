package ua.george_nika.simulation.controller.light_ajax_info;

import ua.george_nika.simulation.model.entity.impl.HumanEntityInfo;

/**
 * Created by george on 21.12.2015.
 */
public class LightHumanEntityInfo {

    private int idEntityInfo;
    private int delayTimeToRemoveMs;
    private int reasonablePriceInCent;

    public LightHumanEntityInfo(HumanEntityInfo humanEntityInfo) {
        this.idEntityInfo = humanEntityInfo.getIdEntityInfo();
        this.delayTimeToRemoveMs = humanEntityInfo.getDelayTimeToRemoveMs();
        this.reasonablePriceInCent = humanEntityInfo.getReasonablePriceInCent();
    }

    public int getIdEntityInfo() {
        return idEntityInfo;
    }

    public void setIdEntityInfo(int idEntityInfo) {
        this.idEntityInfo = idEntityInfo;
    }

    public int getDelayTimeToRemoveMs() {
        return delayTimeToRemoveMs;
    }

    public void setDelayTimeToRemoveMs(int delayTimeToRemoveMs) {
        this.delayTimeToRemoveMs = delayTimeToRemoveMs;
    }

    public int getReasonablePriceInCent() {
        return reasonablePriceInCent;
    }

    public void setReasonablePriceInCent(int reasonablePriceInCent) {
        this.reasonablePriceInCent = reasonablePriceInCent;
    }
}
