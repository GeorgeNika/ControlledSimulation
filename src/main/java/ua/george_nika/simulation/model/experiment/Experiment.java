/**
 * Interface for model object - experiment
 */

package ua.george_nika.simulation.model.experiment;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import ua.george_nika.simulation.model.generator.Generator;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressWarnings("unused")

public interface Experiment {

    void startExecution();

    void pauseExecution();

    void continueExecution();

    void endExecution();

    String getExperimentType();


    // getter and setter from abstract class

    int getIdExperiment();

    void setIdExperiment(int idExperiment);

    String getExperimentName();

    void setExperimentName(String experimentName);

    DateTime getStartTime();

    void setStartTime(DateTime startTime);

    DateTime getEndTime();

    void setEndTime(DateTime endTime);

    int getStepIntervalMs();

    void setStepIntervalMs(int intervalMs);

    DateTime getCreated();

    void setCreated(DateTime created);

    DateTime getUpdated();

    void setUpdated(DateTime updated);

    List<Generator> getGeneratorList();

    void setGeneratorList(List<Generator> generatorList);

    MutableDateTime getCurrentTime();

    void setCurrentTime(MutableDateTime currentTime);

    AtomicBoolean getWorking();

    void setWorking(AtomicBoolean working);

    AtomicBoolean getRunning();

    void setRunning(AtomicBoolean running);

    AtomicBoolean getPaused();

    void setPaused(AtomicBoolean paused);

    ExperimentHistory getExperimentHistory();

    void setExperimentHistory(ExperimentHistory experimentHistory);

}
