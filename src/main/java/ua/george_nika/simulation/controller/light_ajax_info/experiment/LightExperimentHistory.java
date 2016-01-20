/**
 * used for send request when get ajax response
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.light_ajax_info.experiment;

import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.util.TimeToStringUtil;

@SuppressWarnings("unused")

public class LightExperimentHistory {

    protected int idExperimentHistory;
    protected int idExperiment;
    protected String experimentType;
    protected String experimentName;
    protected int generatorQuantity;
    protected String executeDate;
    protected String startTime;
    protected String endTime;
    protected String lastPoint;

    public LightExperimentHistory(ExperimentHistory experimentHistory) {
        this.idExperimentHistory = experimentHistory.getIdExperimentHistory();
        this.idExperiment = experimentHistory.getIdExperiment();
        this.experimentType = experimentHistory.getExperimentType();
        this.experimentName = experimentHistory.getExperimentName();
        this.generatorQuantity = experimentHistory.getGeneratorQuantity();
        this.executeDate = TimeToStringUtil.getSafeString(experimentHistory.getExecuteDate());
        this.startTime = TimeToStringUtil.getSafeString(experimentHistory.getExperimentStart());
        this.endTime = TimeToStringUtil.getSafeString(experimentHistory.getExperimentEnd());
        this.lastPoint = TimeToStringUtil.getSafeString(experimentHistory.getLastPoint());
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

    public int getGeneratorQuantity() {
        return generatorQuantity;
    }

    public void setGeneratorQuantity(int generatorQuantity) {
        this.generatorQuantity = generatorQuantity;
    }

    public String getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(String executeDate) {
        this.executeDate = executeDate;
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

    public String getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(String lastPoint) {
        this.lastPoint = lastPoint;
    }
}
