package ua.george_nika.simulation.controller.form;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.util.AppConst;

/**
 * Created by george on 16.12.2015.
 */
public class ExperimentForm {

    private String experimentName;
    private String startTime;
    private String endTime;
    private int stepIntervalMs;

    public ExperimentForm() {
    }

    public ExperimentForm(Experiment experiment) {
        this.experimentName = experiment.getExperimentName();
        this.startTime = experiment.getStartTime().toString(DateTimeFormat.forPattern(AppConst.DATE_TIME_FORMAT));
        this.endTime = experiment.getEndTime().toString(DateTimeFormat.forPattern(AppConst.DATE_TIME_FORMAT));
        this.stepIntervalMs = experiment.getStepIntervalMs();
    }

    public void updateExperiment(Experiment experiment) {
        experiment.setExperimentName(experimentName);
        DateTimeFormatter formatter = DateTimeFormat.forPattern(AppConst.DATE_TIME_FORMAT);
        experiment.setStartTime(formatter.parseDateTime(startTime));
        experiment.setEndTime(formatter.parseDateTime(endTime));
        experiment.setStepIntervalMs(stepIntervalMs);
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
}
