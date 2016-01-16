package ua.george_nika.simulation.controller.light_ajax_info;

import org.joda.time.format.DateTimeFormat;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.util.AppConst;

/**
 * Created by george on 22.12.2015.
 */
public class LightExperiment {

    protected int idExperiment;
    protected String experimentType;
    protected String experimentName;
    protected String startTime;
    protected String endTime;
    protected int stepIntervalMs;
    protected String created;
    protected String updated;

    public LightExperiment(Experiment experiment) {
        this.idExperiment = experiment.getIdExperiment();
        this.experimentType = experiment.getExperimentType();
        this.experimentName = experiment.getExperimentName();
        this.startTime = experiment.getStartTime().toString(DateTimeFormat.forPattern(AppConst.DATE_TIME_FORMAT));
        this.endTime = experiment.getEndTime().toString(DateTimeFormat.forPattern(AppConst.DATE_TIME_FORMAT));
        this.stepIntervalMs = experiment.getStepIntervalMs();
        this.created = experiment.getCreated().toString(DateTimeFormat.forPattern(AppConst.DATE_TIME_FORMAT));
        this.updated = experiment.getUpdated().toString(DateTimeFormat.forPattern(AppConst.DATE_TIME_FORMAT));
    }

    public int getIdExperiment() {
        return idExperiment;
    }

    public void setIdExperiment(int idExperiment) {
        this.idExperiment = idExperiment;
    }

    public String getExperimentType() {
        return experimentType;
    }

    public void setExperimentType(String experimentType) {
        this.experimentType = experimentType;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getStepIntervalMs() {
        return stepIntervalMs;
    }

    public void setStepIntervalMs(int stepIntervalMs) {
        this.stepIntervalMs = stepIntervalMs;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

}
