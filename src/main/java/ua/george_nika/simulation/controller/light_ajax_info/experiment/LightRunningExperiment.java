package ua.george_nika.simulation.controller.light_ajax_info.experiment;

import org.joda.time.format.DateTimeFormat;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.util.AppConst;

/**
 * Created by george on 05.01.2016.
 */
public class LightRunningExperiment {

    private int idExperimentHistory;
    private int idExperiment;
    private String experimentName;
    private String experimentType;
    private String startDateTime;
    private String endDateTime;
    private String currentDateTime;
    private boolean paused;

    public LightRunningExperiment(Experiment experiment){
        this.idExperimentHistory = experiment.getExperimentHistory().getIdExperimentHistory();
        this.idExperiment = experiment.getIdExperiment();
        this.experimentName = experiment.getExperimentName();
        this.experimentType = experiment.getExperimentType();
        this.startDateTime = experiment.getStartTime().toString(DateTimeFormat.forPattern(AppConst.DATE_TIME_FORMAT));
        this.endDateTime = experiment.getEndTime().toString(DateTimeFormat.forPattern(AppConst.DATE_TIME_FORMAT));
        this.currentDateTime = experiment.getCurrentTime().toString(DateTimeFormat.forPattern(AppConst.DATE_TIME_FORMAT));
        this.paused = experiment.getPaused().get();
    }

    public int getIdExperimentHistory() {
        return idExperimentHistory;
    }

    public void setIdExperimentHistory(int idExperimentHistory) {
        this.idExperimentHistory = idExperimentHistory;
    }

    public int getIdExperiment() {
        return idExperiment;
    }

    public void setIdExperiment(int idExperiment) {
        this.idExperiment = idExperiment;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
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

    public String getExperimentType() {
        return experimentType;
    }

    public void setExperimentType(String experimentType) {
        this.experimentType = experimentType;
    }
}
