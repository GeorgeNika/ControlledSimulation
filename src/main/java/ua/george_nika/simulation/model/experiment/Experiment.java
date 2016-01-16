package ua.george_nika.simulation.model.experiment;


import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import ua.george_nika.simulation.model.generator.Generator;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by george on 23.11.2015.
 */
public interface Experiment {

    void startExecution();

    void pauseExecution();

    void continueExecution();

    void endExecution();

    String getExperimentType();





    public int getIdExperiment();

    public void setIdExperiment(int idExperiment);

    public String getExperimentName();

    public void setExperimentName(String experimentName);

    public DateTime getStartTime();

    public void setStartTime(DateTime startTime);

    public DateTime getEndTime();

    public void setEndTime(DateTime endTime);

    public int getStepIntervalMs();

    public void setStepIntervalMs(int intervalMs);

    public DateTime getCreated();

    public void setCreated(DateTime created);

    public DateTime getUpdated();

    public void setUpdated(DateTime updated);

    public List<Generator> getGeneratorList();

    public void setGeneratorList(List<Generator> generatorList);

    public MutableDateTime getCurrentTime();

    public void setCurrentTime(MutableDateTime currentTime);

    public AtomicBoolean getRunning();

    public void setRunning(AtomicBoolean running);

    public AtomicBoolean getPaused() ;

    public void setPaused(AtomicBoolean paused) ;

    public ExperimentHistory getExperimentHistory();

    public void setExperimentHistory(ExperimentHistory experimentHistory) ;

}
