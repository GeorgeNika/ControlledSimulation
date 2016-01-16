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
public class LightOneRunningGenerator {

    private List<LightEntityRunInfo> entityRunInfoList = new ArrayList<>();
    private String currentDateTime;
    private boolean paused;

    public LightOneRunningGenerator(Experiment experiment) {
        //todo
        if (experiment.getCurrentTime() != null) {
            this.currentDateTime = experiment.getCurrentTime().toString(
                    DateTimeFormat.forPattern(AppConst.DATE_TIME_FORMAT));
        } else {
            this.currentDateTime = "";
        }
        this.paused = experiment.getPaused().get();
        for (Generator loopGenerator : experiment.getGeneratorList()) {
           // generatorRunInfoList.add(new LightGeneratorRunInfo(loopGenerator.getGeneratorHistory()));
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
