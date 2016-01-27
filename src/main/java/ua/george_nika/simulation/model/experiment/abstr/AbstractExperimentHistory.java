/**
 * Common variables and methods for experiment history
 */

package ua.george_nika.simulation.model.experiment.abstr;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;

abstract public class AbstractExperimentHistory implements ExperimentHistory {

    protected int idExperimentHistory;
    protected int idExperiment;
    protected String experimentName;
    protected String experimentType;
    protected int generatorQuantity;
    protected DateTime executeDate;
    protected DateTime experimentStart;
    protected DateTime experimentEnd;
    protected MutableDateTime lastPoint;
    protected String loggerName;
    protected String logFile;
    protected String xmlFile;

    abstract protected void setInitialExperimentHistoryExtraData(Experiment experiment);

    abstract protected void updateExperimentHistoryExtraData(Experiment experiment);

    public void setInitialExperimentHistoryData(Experiment experiment) {
        this.idExperiment = experiment.getIdExperiment();
        this.experimentName = experiment.getExperimentName();
        this.experimentType = experiment.getExperimentType();
        this.generatorQuantity = experiment.getGeneratorList().size();
        this.executeDate = new DateTime();
        this.experimentStart = experiment.getStartTime();
        this.experimentEnd = experiment.getEndTime();
        this.lastPoint = experiment.getStartTime().toMutableDateTime();

        setInitialExperimentHistoryExtraData(experiment);
    }

    public void updateExperimentHistoryData(Experiment experiment) {
        setLastPoint(experiment.getCurrentTime());

        updateExperimentHistoryExtraData(experiment);
    }

    @Override
    public String getLogIdentifyMessage() {
        return "[exp hist : " + idExperimentHistory + " ] - ";
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

    public String getExperimentType() {
        return experimentType;
    }

    public void setExperimentType(String experimentType) {
        this.experimentType = experimentType;
    }

    public int getGeneratorQuantity() {
        return generatorQuantity;
    }

    public void setGeneratorQuantity(int generatorQuantity) {
        this.generatorQuantity = generatorQuantity;
    }

    public DateTime getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(DateTime executeDate) {
        this.executeDate = executeDate;
    }

    public DateTime getExperimentStart() {
        return experimentStart;
    }

    public void setExperimentStart(DateTime experimentStart) {
        this.experimentStart = experimentStart;
    }

    public DateTime getExperimentEnd() {
        return experimentEnd;
    }

    public void setExperimentEnd(DateTime experimentEnd) {
        this.experimentEnd = experimentEnd;
    }

    public MutableDateTime getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(MutableDateTime lastPoint) {
        this.lastPoint = lastPoint;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLogFile() {
        return logFile;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    public String getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(String xmlFile) {
        this.xmlFile = xmlFile;
    }
}
