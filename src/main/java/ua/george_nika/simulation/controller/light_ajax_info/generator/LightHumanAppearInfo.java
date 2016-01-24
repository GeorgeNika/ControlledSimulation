/**
 * used for send request when get ajax response
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.light_ajax_info.generator;

import ua.george_nika.simulation.model.generator.impl.HumanAppearInfo;

@SuppressWarnings("unused")

public class LightHumanAppearInfo {

    protected int idHumanAppearInfo;
    protected int startTimeMs;
    protected int endTimeMs;
    protected double percent;
    protected double variation;

    public LightHumanAppearInfo(HumanAppearInfo humanAppearInfo) {
        this.idHumanAppearInfo = humanAppearInfo.getIdHumanAppearInfo();
        this.startTimeMs = humanAppearInfo.getStartTimeMs();
        this.endTimeMs = humanAppearInfo.getEndTimeMs();
        this.percent = humanAppearInfo.getPercent();
        this.variation = humanAppearInfo.getVariation();
    }


    public int getIdHumanAppearInfo() {
        return idHumanAppearInfo;
    }

    public void setIdHumanAppearInfo(int idHumanAppearInfo) {
        this.idHumanAppearInfo = idHumanAppearInfo;
    }

    public int getStartTimeMs() {
        return startTimeMs;
    }

    public void setStartTimeMs(int startTimeMs) {
        this.startTimeMs = startTimeMs;
    }

    public int getEndTimeMs() {
        return endTimeMs;
    }

    public void setEndTimeMs(int endTimeMs) {
        this.endTimeMs = endTimeMs;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public double getVariation() {
        return variation;
    }

    public void setVariation(double variation) {
        this.variation = variation;
    }
}
