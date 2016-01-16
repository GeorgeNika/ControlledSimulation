package ua.george_nika.simulation.controller.light_ajax_info;

import ua.george_nika.simulation.model.generator.impl.HumanAppearInfo;

/**
 * Created by george on 18.12.2015.
 */
public class LightHumanAppearInfo {

    protected int idHumanAppearInfo;
    protected int startTimeMs;
    protected int endTimeMs;
    protected double percent;

    public LightHumanAppearInfo(HumanAppearInfo humanAppearInfo) {
        this.idHumanAppearInfo = humanAppearInfo.getIdHumanAppearInfo();
        this.startTimeMs = humanAppearInfo.getStartTimeMs();
        this.endTimeMs = humanAppearInfo.getEndTimeMs();
        this.percent = humanAppearInfo.getPercent();
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
}
