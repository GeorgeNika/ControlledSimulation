package ua.george_nika.simulation.service.experiment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.experiment.ExperimentDao;
import ua.george_nika.simulation.dao.experiment.ExperimentExtraDao;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.service.error.ExperimentException;
import ua.george_nika.simulation.service.generator.GeneratorService;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.RunningExperimentHolder;

import java.util.List;

/**
 * Created by george on 07.12.2015.
 */
@Service
public class ExperimentService {
    private static String LOGGER_NAME = AppLog.SERVICE;
    private static String CLASS_NAME = ExperimentService.class.getName();

    private ExperimentExtraDao experimentExtraDao;

    @Autowired
    private ExperimentDao experimentDao;

    @Autowired
    private GeneratorService generatorService;

    @Autowired
    private ExperimentHistoryService experimentHistoryService;


    public Experiment getExperimentById(int idExperiment) {
        try {
            Experiment resultExperiment = getLazyExperimentById(idExperiment);
            experimentExtraDao = DaoFactory.getExperimentExtraDaoByType(resultExperiment.getExperimentType());
            experimentExtraDao.addExtraDataToExperiment(resultExperiment);
            List<Generator> tempGenList = generatorService.getAllGeneratorByExperimentId(idExperiment);
            resultExperiment.setGeneratorList(tempGenList);
            return resultExperiment;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get experiment by id - " + idExperiment, ex);
            throw new ExperimentException("Error in get experiment by id - " + idExperiment);
        }
    }

    public Experiment getLazyExperimentById(int idExperiment) {
        try {
            Experiment resultExperiment;
            resultExperiment = experimentDao.getLazyExperimentById(idExperiment);
            return resultExperiment;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get lazy experiment by id - " + idExperiment, ex);
            throw new ExperimentException("Error in get lazy experiment by id - " + idExperiment);
        }
    }

    public List<Experiment> getAllLazyExperiment() {
        try {
            List<Experiment> resultExperimentList;
            resultExperimentList = experimentDao.getAllLazyExperiment();
            return resultExperimentList;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get all lazy experiment", ex);
            throw new ExperimentException("Error in get all lazy experiment");
        }
    }

    public void createExperimentByType(String experimentType) {
        try {
            //todo in future.     It would be better if it will be in one transaction
            int resultId = experimentDao.createEmptyExperimentByTypeAndGetNewId(experimentType);
            ExperimentExtraDao experimentExtraDao = DaoFactory.getExperimentExtraDaoByType(experimentType);
            experimentExtraDao.createNewExperimentExtraDataById(resultId);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in create new experiment by type - " + experimentType, ex);
            throw new ExperimentException("Error in create new experiment by type - " + experimentType);
        }
    }

    public void updateLazyExperiment(Experiment experiment) {
        try {
            experimentDao.updateLazyExperiment(experiment);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in update lazy experiment id - " + experiment.getIdExperiment(), ex);
            throw new ExperimentException("Error in update lazy experiment id - " + experiment.getIdExperiment());
        }
    }

    public void deleteExperimentById(int idExperiment) {
        try {
            experimentDao.deleteLazyExperimentById(idExperiment);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in delete experiment by id - " + idExperiment, ex);
            throw new ExperimentException("Error in delete experiment by id - " + idExperiment);
        }
    }


    public void addGeneratorToExperimentList(int idExperiment, int idGenerator) {
        try {
            for (Generator loopG : generatorService.getAllGeneratorByExperimentId(idExperiment)) {
                if (loopG.getIdGenerator() == idGenerator) {
                    return;
                }
            }
            experimentDao.addGeneratorToExperimentList(idExperiment, idGenerator);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in add generator id - " + idGenerator +
                    " to experiment id - " + idExperiment, ex);
            throw new ExperimentException("Error in add generator id - " + idGenerator +
                    " to experiment id - " + idExperiment);
        }
    }

    public void deleteGeneratorFromExperimentList(int idExperiment, int idGenerator) {
        try {
            experimentDao.deleteGeneratorFromExperimentList(idExperiment, idGenerator);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in delete generator id - " + idGenerator +
                    " from experiment id - " + idExperiment, ex);
            throw new ExperimentException("Error in delete generator id - " + idGenerator +
                    " from experiment id - " + idExperiment);
        }
    }


    public void updateExperimentExtraData(Experiment experiment) {
        try {
            ExperimentExtraDao experimentExtraDao = DaoFactory.getExperimentExtraDaoByType(experiment.getExperimentType());
            experimentExtraDao.updateExperimentExtraData(experiment);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in update extra data in experiment id - " +
                    experiment.getIdExperiment(), ex);
            throw new ExperimentException("Error in update extra data in experiment id - " + experiment.getIdExperiment());
        }
    }

    public void changeExperimentType(int idExperiment, String newExperimentType) {
        try {
            Experiment tempE = experimentDao.getLazyExperimentById(idExperiment);
            if (newExperimentType.equals(tempE.getExperimentType())) {
                return;
            }

            //todo in future.     It would be better if it will be in one transaction
            //create new extraData
            experimentExtraDao = DaoFactory.getExperimentExtraDaoByType(newExperimentType);
            experimentExtraDao.createNewExperimentExtraDataById(idExperiment);

            //change type in experiment
            experimentDao.changeExperimentType(idExperiment, newExperimentType);

            // delete old extraData
            ExperimentExtraDao experimentExtraDao = DaoFactory.getExperimentExtraDaoByType(tempE.getExperimentType());
            experimentExtraDao.deleteExperimentExtraData(idExperiment);

        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in change experiment type id - " + idExperiment +
                    " to type - " + newExperimentType, ex);
            throw new ExperimentException("Error in change experiment type id - " + idExperiment +
                    " to type - " + newExperimentType);
        }

    }

    public void startExperiment(Experiment experiment) {
        try {
            experiment.startExecution();
            RunningExperimentHolder.addRunningExperiment(experiment);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in start experiment id - " + experiment.getIdExperiment(), ex);
            throw new ExperimentException("Error in start experiment id - " + experiment.getIdExperiment());
        }
    }

    public void stopExperiment(Experiment experiment) {
        try {
            experiment.endExecution();
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in stop experiment id - " + experiment.getIdExperiment(), ex);
            throw new ExperimentException("Error in stop experiment id - " + experiment.getIdExperiment());
        }
    }

    public void pauseExperiment(Experiment experiment) {
        try {
            experiment.pauseExecution();
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in pause experiment id - " + experiment.getIdExperiment(), ex);
            throw new ExperimentException("Error in pause experiment id - " + experiment.getIdExperiment());
        }
    }

    public void continueExperiment(Experiment experiment) {
        try {
            // todo in future.    We must Check about changes of experiment.
            experiment.continueExecution();
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in continue experiment id - " + experiment.getIdExperiment(), ex);
            throw new ExperimentException("Error in continue experiment id - " + experiment.getIdExperiment());
        }
    }

}
