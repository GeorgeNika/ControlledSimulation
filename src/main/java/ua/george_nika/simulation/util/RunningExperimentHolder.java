package ua.george_nika.simulation.util;

import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.service.error.NoExperimentWithHistory;
import ua.george_nika.simulation.service.error.RunningExperimentException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by george on 06.01.2016.
 */
public class RunningExperimentHolder {
    private static String LOGGER_NAME = AppLog.UTIL;
    private static String CLASS_NAME = RunningExperimentHolder.class.getName();

    private static volatile List<Experiment> runningExperimentList = new ArrayList<>();

    private RunningExperimentHolder() {
    }

    public static synchronized List<Experiment> getRunningExperimentList() {
        try {
            Iterator<Experiment> iterator = runningExperimentList.iterator();
            Experiment tempExp;
            while (iterator.hasNext()) {
                tempExp = iterator.next();
                if (!tempExp.getRunning().get()) {
                    iterator.remove();
                }
            }
            return runningExperimentList;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get running experiment list", ex);
            throw new RunningExperimentException("Error in get running experiment list");
        }
    }

    public static synchronized void addRunningExperiment(Experiment experiment) {
        try{
            runningExperimentList.add(experiment);
        }catch (RuntimeException ex){
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in add experiment to running experiment list", ex);
            throw new RunningExperimentException("Error in add experiment to running experiment list");
        }

    }

    public static synchronized Experiment getExperimentByExperimentHistoryId(int idExperimentHistory) {
        try {
            for (Experiment loopExp : runningExperimentList) {
                if (loopExp.getExperimentHistory().getIdExperimentHistory() == idExperimentHistory) {
                    return loopExp;
                }
            }
            throw new NoExperimentWithHistory();
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in getting experiment by history id - " +
                    idExperimentHistory, ex);
            throw new RunningExperimentException("Error in getting experiment by history id - " +
                    idExperimentHistory);
        }

    }


}
