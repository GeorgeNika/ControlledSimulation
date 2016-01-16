package ua.george_nika.simulation.model.experiment.abstr;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.service.experiment.ExperimentHistoryService;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.DateTimeXmlAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by george on 23.11.2015.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
abstract public class AbstractExperiment implements Experiment, Runnable {

    protected int idExperiment;
    protected String experimentName;

    @XmlJavaTypeAdapter(DateTimeXmlAdapter.class)
    protected DateTime startTime;

    @XmlJavaTypeAdapter(DateTimeXmlAdapter.class)
    protected DateTime endTime;

    protected int stepIntervalMs;

    @XmlJavaTypeAdapter(DateTimeXmlAdapter.class)
    protected DateTime created;

    @XmlJavaTypeAdapter(DateTimeXmlAdapter.class)
    protected DateTime updated;

    @XmlElementWrapper
    @XmlAnyElement(lax = true)
    protected List<Generator> generatorList = new ArrayList<Generator>();

    @XmlTransient
    protected MutableDateTime currentTime;
    @XmlTransient
    protected volatile AtomicBoolean running = new AtomicBoolean();
    @XmlTransient
    protected volatile AtomicBoolean paused = new AtomicBoolean();
    @XmlTransient
    protected volatile AtomicBoolean workingState = new AtomicBoolean();
    @XmlTransient
    protected ExperimentHistory experimentHistory;

    public void startExecution() {
        checkIsExperimentCorrect();
        initExperimentWorkVariable();
        initAllGenerator();
        afterInitAllGenerator();
        saveAllHistory();

        Thread thread = new Thread(this);
        thread.start();
    }

    protected void checkIsExperimentCorrect() {
        // todo in future
    }

    protected void initExperimentWorkVariable() {
        currentTime = startTime.toMutableDateTime();
        running.set(true);
        paused.set(false);
        workingState.set(false);
        experimentHistory = ExperimentHistoryService.getNewExperimentHistory(this);
        AppLog.info(experimentHistory.getLoggerName(), experimentHistory.getLogIdentifyMessage()
                + "start experiment : " + idExperiment + " - " + experimentName
                + " with history id - " + experimentHistory.getIdExperimentHistory());
        AppLog.info(experimentHistory.getLoggerName(), experimentHistory.getLogIdentifyMessage()
                + "init experiment variable");
        AppLog.info(experimentHistory.getLoggerName(), experimentHistory.getLogIdentifyMessage()
                + "start time : " + startTime);
    }

    protected void initAllGenerator() {
        for (Generator loopGenerator : generatorList) {
            loopGenerator.initAction(this);
        }
    }

    protected void afterInitAllGenerator() {
        for (Generator loopGenerator : generatorList) {
            loopGenerator.afterInitAction(this);
        }
    }

    public void pauseExecution() {
        AppLog.info(experimentHistory.getLoggerName(), experimentHistory.getLogIdentifyMessage()
                + "pause time : " + currentTime);
        paused.set(true);
        saveAllHistory();

    }

    public void continueExecution() {
        AppLog.info(experimentHistory.getLoggerName(), experimentHistory.getLogIdentifyMessage()
                + "continue time : " + currentTime);
        paused.set(false);
    }

    public void endExecution() {
        AppLog.info(experimentHistory.getLoggerName(), experimentHistory.getLogIdentifyMessage()
                + "stop time : " + currentTime);
        paused.set(false);
        running.set(false);
        updateAllHistory();
        saveAllHistory();
    }

    public void run() {
        while (running.get()) {

            if (currentTime.isEqual(endTime)) {
                endExecution();
                continue;
            }

            if (paused.get()) {
                executePause();
                continue;
            } else {
                workingState.set(true);
            }

            setNewExperimentTime();
            executeMainActionForAllGenerator();
            executeDependentActionForAllGenerator();
            updateAllHistory();
            setNewCurrentTimeForAllGenerator();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                endExecution();
            }
        }
    }

    protected void executePause() {
        try {
            workingState.set(false);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            endExecution();
        }
    }

    protected void setNewExperimentTime() {
        currentTime.addMillis(stepIntervalMs);
        if (currentTime.isAfter(endTime)) {
            currentTime.setDate(endTime);
        }
        AppLog.info(experimentHistory.getLoggerName(), experimentHistory.getLogIdentifyMessage()
                + "set new experiment time " + currentTime.toString());
    }

    protected void executeMainActionForAllGenerator() {
        for (Generator loopGenerator : generatorList) {
            loopGenerator.executeMainAction(currentTime);
        }
    }

    protected void executeDependentActionForAllGenerator() {
        for (Generator loopGenerator : generatorList) {
            loopGenerator.executeDependentAction(currentTime);
        }
    }

    protected void setNewCurrentTimeForAllGenerator() {
        for (Generator loopGenerator : generatorList) {
            loopGenerator.setNewCurrentTime(currentTime);
        }
    }

    protected void saveAllHistory() {
        ExperimentHistoryService.saveExperimentHistory(experimentHistory);
        for (Generator loopGenerator : generatorList) {
            loopGenerator.saveGeneratorHistory();
        }
    }

    protected void updateAllHistory() {
        experimentHistory.updateExperimentHistoryData(this);
        for (Generator loopGenerator : generatorList) {
            loopGenerator.updateGeneratorHistory();
        }
    }


    //*********** Auto generated

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

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public int getStepIntervalMs() {
        return stepIntervalMs;
    }

    public void setStepIntervalMs(int stepIntervalMs) {
        this.stepIntervalMs = stepIntervalMs;
    }

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public DateTime getUpdated() {
        return updated;
    }

    public void setUpdated(DateTime updated) {
        this.updated = updated;
    }

    public List<Generator> getGeneratorList() {
        return generatorList;
    }

    public void setGeneratorList(List<Generator> generatorList) {
        this.generatorList = generatorList;
    }

    public MutableDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(MutableDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public AtomicBoolean getRunning() {
        return running;
    }

    public void setRunning(AtomicBoolean running) {
        this.running = running;
    }

    public AtomicBoolean getPaused() {
        return paused;
    }

    public void setPaused(AtomicBoolean paused) {
        this.paused = paused;
    }

    public ExperimentHistory getExperimentHistory() {
        return experimentHistory;
    }

    public void setExperimentHistory(ExperimentHistory experimentHistory) {
        this.experimentHistory = experimentHistory;
    }


}
