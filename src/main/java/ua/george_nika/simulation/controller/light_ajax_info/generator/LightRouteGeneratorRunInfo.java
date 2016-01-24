/**
 * used for send request when get ajax response
 * after lecture  JavaDoc + UnitTest = Documentation
 */
package ua.george_nika.simulation.controller.light_ajax_info.generator;

import ua.george_nika.simulation.controller.light_ajax_info.entity.LightEntityRunInfo;
import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.util.TimeToStringUtil;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")

public class LightRouteGeneratorRunInfo {

    private List<LightEntityRunInfo> entityRunInfoList = new ArrayList<>();
    private String currentDateTime;
    private boolean paused;

    public LightRouteGeneratorRunInfo(Experiment experiment, Generator generator) {
        this.currentDateTime = TimeToStringUtil.getSafeString(experiment.getCurrentTime());
        this.paused = experiment.getPaused().get();
        for (Entity loopEntity : generator.getDependentEntityList()) {
            entityRunInfoList.add(new LightEntityRunInfo(loopEntity.getEntityHistory()));
        }
    }

    public List<LightEntityRunInfo> getEntityRunInfoList() {
        return entityRunInfoList;
    }

    public void setEntityRunInfoList(List<LightEntityRunInfo> entityRunInfoList) {
        this.entityRunInfoList = entityRunInfoList;
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
