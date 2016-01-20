/**
 * used for send request when get ajax response
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.light_ajax_info.generator;

import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.util.TimeToStringUtil;

@SuppressWarnings("unused")

public class LightStationGeneratorRunningInfo {

    private String entityInfo;
    private String currentDateTime;
    private boolean paused;

    public LightStationGeneratorRunningInfo(Experiment experiment, Generator generator) {
        this.currentDateTime = TimeToStringUtil.getSafeString(experiment.getCurrentTime());
        this.paused = experiment.getPaused().get();
        this.entityInfo = generator.getEntityType() + " count - " + generator.getDependentEntityList().size();
    }

    public String getEntityInfo() {
        return entityInfo;
    }

    public void setEntityInfo(String entityInfo) {
        this.entityInfo = entityInfo;
    }

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
