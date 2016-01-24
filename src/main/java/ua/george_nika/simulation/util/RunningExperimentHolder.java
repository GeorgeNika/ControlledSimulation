/**
 * Holder for running experiment
 *
 * This class hold all running experiments for application (all session)
 */

package ua.george_nika.simulation.util;

import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.service.error.NoSuchHistory;
import ua.george_nika.simulation.service.error.RunningHistoryException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("unused")

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
                if (!tempExp.getWorking().get()) {
                    iterator.remove();
                }
            }
            return runningExperimentList;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error when get running experiment list", ex);
            throw new RunningHistoryException("Error when get running experiment list");
        }
    }

    public static synchronized void addRunningExperiment(Experiment experiment) {
        try {
            runningExperimentList.add(experiment);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in add experiment to running experiment list", ex);
            throw new RunningHistoryException("Error in add experiment to running experiment list");
        }

    }

    public static synchronized Experiment getRunningExperiment(int idExperimentHistory) {
        try {
            for (Experiment loopExp : runningExperimentList) {
                if (loopExp.getExperimentHistory().getIdExperimentHistory() == idExperimentHistory) {
                    return loopExp;
                }
            }
            throw new NoSuchHistory();
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error when get running experiment by history id - " +
                    idExperimentHistory, ex);
            throw new RunningHistoryException("Error when get running experiment by history id - " +
                    idExperimentHistory);
        }
    }

    public static synchronized boolean isNotRunningExperiment(int idExperimentHistory) {
        try {
            for (Experiment loopExp : runningExperimentList) {
                if (loopExp.getExperimentHistory().getIdExperimentHistory() == idExperimentHistory) {
                    return false;
                }
            }
            return true;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error when checking experiment by history id - " +
                    idExperimentHistory, ex);
            throw new RunningHistoryException("Error when checking experiment by history id - " +
                    idExperimentHistory);
        }
    }

    public static synchronized Generator getRunningGenerator(int idExperimentHistory, int idGeneratorHistory) {
        try {
            Experiment experiment = getRunningExperiment(idExperimentHistory);
            for (Generator loopGen : experiment.getGeneratorList()) {
                if (loopGen.getGeneratorHistory().getIdGeneratorHistory() == idGeneratorHistory) {
                    return loopGen;
                }
            }
            throw new NoSuchHistory();
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error when get running generator by history id - " +
                    idExperimentHistory + " in experiment with history id - " + idExperimentHistory, ex);
            throw new RunningHistoryException("Error when get running generator by history id - " +
                    idExperimentHistory + " in experiment with history id - " + idExperimentHistory);
        }
    }

    public static synchronized Entity getRunningEntity(int idExperimentHistory, int idGeneratorHistory,
                                                       int idEntityHistory) {
        try {
            Generator generator = getRunningGenerator(idExperimentHistory, idGeneratorHistory);
            for (Entity loopEntity : generator.getDependentEntityList()) {
                if (loopEntity.getEntityHistory().getIdEntityHistory() == idEntityHistory) {
                    return loopEntity;
                }
            }
            throw new NoSuchHistory();
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error when get running entity by history id - " + idEntityHistory
                    + " in generator with history id - " + idExperimentHistory
                    + " in experiment with history id - " + idExperimentHistory, ex);
            throw new RunningHistoryException("Error when get running entity by history id - " + idEntityHistory
                    + " in generator with history id - " + idExperimentHistory
                    + " in experiment with history id - " + idExperimentHistory);
        }
    }


}
