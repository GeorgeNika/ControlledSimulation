package ua.george_nika.simulation.service.experiment;

import org.springframework.stereotype.Service;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.experiment.ExperimentHistoryDao;
import ua.george_nika.simulation.dao.experiment.ExperimentHistoryExtraDao;
import ua.george_nika.simulation.dao.filter.ExperimentFilter;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.model.experiment.ExperimentHistoryFactory;
import ua.george_nika.simulation.service.error.ExperimentHistoryException;
import ua.george_nika.simulation.service.error.RunningHistoryException;
import ua.george_nika.simulation.util.*;

import java.util.List;

/**
 * Created by george on 05.01.2016.
 */
@Service
public class ExperimentHistoryService {

    private static String LOGGER_NAME = AppLog.SERVICE;
    private static String CLASS_NAME = ExperimentService.class.getName();

    public static ExperimentHistory getNewExperimentHistory(Experiment experiment) {
        try {
            //create new history
            ExperimentHistory experimentHistory
                    = ExperimentHistoryFactory.getExperimentHistoryByType(experiment.getExperimentType());

            //set data from experiment
            experimentHistory.setInitialExperimentHistoryData(experiment);

            //create record in common history db
            ExperimentHistoryDao experimentHistoryDao = DaoFactory.getExperimentHistoryDao();
            int idExperimentHistory = experimentHistoryDao.createNewExperimentHistoryRecord(experimentHistory);
            experimentHistory.setIdExperimentHistory(idExperimentHistory);

            //create record in extra history db
            ExperimentHistoryExtraDao experimentHistoryExtraDao
                    = DaoFactory.getExperimentHistoryExtraDaoByType(experiment.getExperimentType());
            experimentHistoryExtraDao.createNewExperimentHistoryExtraRecordById(idExperimentHistory);

            //create log and xml file
            String infoString = experimentHistory.getIdExperimentHistory() + " "
                    + experiment.getExperimentType() + " "
                    + experiment.getIdExperiment() + " " + experiment.getExperimentName();
            experimentHistory.setLoggerName(AppLog.getEmptyLoggerName(infoString));
            experimentHistory.setLogFile(AppLog.getLoggerFileName(experimentHistory.getLoggerName()));
            experimentHistory.setXmlFile(XmlUtil.saveExperimentToXml(experiment, infoString));

            //first save in db
            saveExperimentHistory(experimentHistory);

            return experimentHistory;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in creating history for experiment id - " +
                    experiment.getIdExperiment(), ex);
            throw new ExperimentHistoryException("Error in creating history for experiment id - " +
                    experiment.getIdExperiment());
        }
    }

    public static void saveExperimentHistory(ExperimentHistory experimentHistory) {
        try {
            ExperimentHistoryDao experimentHistoryDao = DaoFactory.getExperimentHistoryDao();
            experimentHistoryDao.saveExperimentHistory(experimentHistory);
            ExperimentHistoryExtraDao experimentHistoryExtraDao
                    = DaoFactory.getExperimentHistoryExtraDaoByType(experimentHistory.getExperimentType());
            experimentHistoryExtraDao.saveExperimentHistoryExtraData(experimentHistory);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in saving data for experiment history id - " +
                    experimentHistory.getIdExperimentHistory(), ex);
            throw new ExperimentHistoryException("Error in saving data for experiment history id - " +
                    experimentHistory.getIdExperimentHistory());
        }
    }

    public static void closeExperimentHistory(ExperimentHistory experimentHistory) {
        try {
            AppLog.freeLoggerName(experimentHistory.getLoggerName());
            String oldLogFileName = experimentHistory.getLogFile();
            experimentHistory.setLogFile(ZipUtil.createZipFile(AppConst.getPathLog(), oldLogFileName));
            saveExperimentHistory(experimentHistory);
            FileUtil.deleteFile(AppConst.getPathLog(), oldLogFileName);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in closing data for experiment history id - " +
                    experimentHistory.getIdExperimentHistory(), ex);
            throw new ExperimentHistoryException("Error in closing data for experiment history id - " +
                    experimentHistory.getIdExperimentHistory());
        }
    }

    public ExperimentHistory getExperimentHistoryById(int idExperimentHistory) {
        try {
            ExperimentHistory resultExperimentHistory = getLazyExperimentHistoryById(idExperimentHistory);
            ExperimentHistoryExtraDao experimentHistoryExtraDao = DaoFactory.getExperimentHistoryExtraDaoByType(
                    resultExperimentHistory.getExperimentType());
            experimentHistoryExtraDao.addExtraDataToExperimentHistory(resultExperimentHistory);
            return resultExperimentHistory;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get experiment history by id history - "
                    + idExperimentHistory, ex);
            throw new ExperimentHistoryException("Error in get experiment history by id history - "
                    + idExperimentHistory);
        }
    }

    public ExperimentHistory getLazyExperimentHistoryById(int idExperimentHistory) {
        try {
            ExperimentHistory resultExperimentHistory;
            ExperimentHistoryDao experimentHistoryDao = DaoFactory.getExperimentHistoryDao();
            resultExperimentHistory = experimentHistoryDao.getLazyExperimentHistoryById(idExperimentHistory);
            return resultExperimentHistory;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get lazy experiment history by id history - "
                    + idExperimentHistory, ex);
            throw new ExperimentHistoryException("Error in get lazy experiment history by id history - "
                    + idExperimentHistory);
        }
    }

    public List<ExperimentHistory> getAllLazyExperimentHistory() {
        try {
            List<ExperimentHistory> resultExperimentHistoryList;
            ExperimentHistoryDao experimentHistoryDao = DaoFactory.getExperimentHistoryDao();
            resultExperimentHistoryList = experimentHistoryDao.getAllLazyExperimentHistory();
            return resultExperimentHistoryList;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get all lazy experiment history", ex);
            throw new ExperimentHistoryException("Error in get all lazy experiment history");
        }
    }

    public List<ExperimentHistory> getLazyExperimentHistoryListByFilter(ExperimentFilter experimentFilter) {
        try {
            List<ExperimentHistory> resultExperimentHistoryList;
            ExperimentHistoryDao experimentHistoryDao = DaoFactory.getExperimentHistoryDao();
            resultExperimentHistoryList = experimentHistoryDao.getLazyExperimentHistoryListByFilter(experimentFilter);
            return resultExperimentHistoryList;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get lazy experiment history list by filter", ex);
            throw new ExperimentHistoryException("Error in get lazy experiment history list by filter");
        }
    }

    public void deleteExperimentHistoryById(int idExperimentHistory) {
        try {
            if (RunningExperimentHolder.isNotRunningExperiment(idExperimentHistory)) {
                ExperimentHistory experimentHistory = getExperimentHistoryById(idExperimentHistory);
                FileUtil.deleteFile(AppConst.getPathLog(),experimentHistory.getLogFile());
                FileUtil.deleteFile(AppConst.getPathXml(),experimentHistory.getXmlFile());
                ExperimentHistoryDao experimentHistoryDao = DaoFactory.getExperimentHistoryDao();
                experimentHistoryDao.deleteExperimentHistoryById(idExperimentHistory);
            } else {
                throw new RunningHistoryException("Try delete running experiment history id - " + idExperimentHistory);
            }
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in delete experiment history by id - "
                    + idExperimentHistory, ex);
            throw new ExperimentHistoryException("Error in delete experiment history by id - "
                    + idExperimentHistory);
        }
    }
}
