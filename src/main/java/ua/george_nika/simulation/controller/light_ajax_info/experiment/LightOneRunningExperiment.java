/**
 * used for send request when get ajax response
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.light_ajax_info.experiment;

import ua.george_nika.simulation.controller.light_ajax_info.generator.LightGeneratorRunInfo;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.util.TimeToStringUtil;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")

public class LightOneRunningExperiment {

    private List<LightGeneratorRunInfo> generatorRunInfoList = new ArrayList<>();
    private String currentDateTime;
    private boolean paused;

    public LightOneRunningExperiment(Experiment experiment) {
        this.currentDateTime = TimeToStringUtil.getSafeString(experiment.getCurrentTime());
        this.paused = experiment.getPaused().get();
        for (Generator loopGenerator : experiment.getGeneratorList()) {
            generatorRunInfoList.add(new LightGeneratorRunInfo(loopGenerator.getGeneratorHistory()));
        }
    }

    public List<LightGeneratorRunInfo> getGeneratorRunInfoList() {
        return generatorRunInfoList;
    }

    public void setGeneratorRunInfoList(List<LightGeneratorRunInfo> generatorRunInfoList) {
        this.generatorRunInfoList = generatorRunInfoList;
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
