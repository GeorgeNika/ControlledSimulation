package ua.george_nika.simulation.controller.light_ajax_info;

import org.joda.time.format.DateTimeFormat;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.util.AppConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 12.01.2016.
 */
public class LightOneRunningExperiment {

    private List<LightGeneratorRunInfo> generatorRunInfoList = new ArrayList<>();
    private String currentDateTime;
    private boolean paused;

    public LightOneRunningExperiment(Experiment experiment) {
        if (experiment.getCurrentTime() != null) {
            this.currentDateTime = experiment.getCurrentTime().toString(
                    DateTimeFormat.forPattern(AppConst.DATE_TIME_FORMAT));
        } else {
            this.currentDateTime = "";
        }
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
