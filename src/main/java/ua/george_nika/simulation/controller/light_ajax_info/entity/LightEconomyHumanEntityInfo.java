/**
 * used for send request when get ajax response
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.light_ajax_info.entity;

import ua.george_nika.simulation.model.entity.impl.EconomyHumanEntityInfo;

@SuppressWarnings("unused")

public class LightEconomyHumanEntityInfo {

    private int idEntityInfo;
    private int delayTimeToRemoveMs;
    private int reasonablePriceInCent;
    private int maxPriceInCent;

    public LightEconomyHumanEntityInfo(EconomyHumanEntityInfo economyHumanEntityInfo) {
        this.idEntityInfo = economyHumanEntityInfo.getIdEntityInfo();
        this.delayTimeToRemoveMs = economyHumanEntityInfo.getDelayTimeToRemoveMs();
        this.reasonablePriceInCent = economyHumanEntityInfo.getReasonablePriceInCent();
        this.maxPriceInCent = economyHumanEntityInfo.getMaxPriceInCent();
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

    public int getMaxPriceInCent() {
        return maxPriceInCent;
    }

    public void setMaxPriceInCent(int maxPriceInCent) {
        this.maxPriceInCent = maxPriceInCent;
    }
}
