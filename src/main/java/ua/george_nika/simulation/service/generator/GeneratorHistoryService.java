package ua.george_nika.simulation.service.generator;

import org.springframework.stereotype.Service;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.generator.GeneratorHistoryDao;
import ua.george_nika.simulation.dao.generator.GeneratorHistoryExtraDao;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.GeneratorHistory;
import ua.george_nika.simulation.model.generator.GeneratorHistoryFactory;
import ua.george_nika.simulation.service.error.GeneratorHistoryException;
import ua.george_nika.simulation.util.AppLog;

import java.util.List;

/**
 * Created by george on 10.01.2016.
 */
@Service
public class GeneratorHistoryService {
    private static String LOGGER_NAME = AppLog.SERVICE;
    private static String CLASS_NAME = GeneratorHistoryService.class.getName();

    public static GeneratorHistory getNewGeneratorHistory(Experiment experiment, Generator generator) {
        try {
            //create new history
            GeneratorHistory generatorHistory
                    = GeneratorHistoryFactory.getGeneratorHistoryByType(generator.getGeneratorType());

            //set data from experiment and generator
            generatorHistory.setInitialGeneratorHistoryData(experiment.getExperimentHistory(), generator);

            //create record in common history db
            GeneratorHistoryDao generatorHistoryDao = DaoFactory.getGeneratorHistoryDao();
            int idGeneratorHistory = generatorHistoryDao.createNewGeneratorHistoryRecord(generatorHistory);
            generatorHistory.setIdGeneratorHistory(idGeneratorHistory);

            //create record in extra history db

            GeneratorHistoryExtraDao generatorHistoryExtraDao
                    = DaoFactory.getGeneratorHistoryExtraDaoByType(generatorHistory.getGeneratorType());
            generatorHistoryExtraDao.createNewGeneratorHistoryExtraRecordById(idGeneratorHistory);

            //first save in db
            saveGeneratorHistory(generatorHistory);

            return generatorHistory;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in creating generator history for experiment id - " +
                    experiment.getIdExperiment() + " and generator id - " + generator.getIdGenerator(), ex);
            throw new GeneratorHistoryException("Error in creating generator history for experiment id - " +
                    experiment.getIdExperiment()+ " and generator id - " + generator.getIdGenerator());
        }
    }

    public static void saveGeneratorHistory(GeneratorHistory generatorHistory) {
        try {
            GeneratorHistoryDao generatorHistoryDao = DaoFactory.getGeneratorHistoryDao();
            generatorHistoryDao.saveGeneratorHistory(generatorHistory);
            GeneratorHistoryExtraDao generatorHistoryExtraDao
                    = DaoFactory.getGeneratorHistoryExtraDaoByType(generatorHistory.getGeneratorType());
            generatorHistoryExtraDao.saveGeneratorHistoryExtraData(generatorHistory);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in saving data for generator history id - " +
                    generatorHistory.getIdGeneratorHistory(), ex);
            throw new GeneratorHistoryException("Error in saving data for generator history id - " +
                    generatorHistory.getIdGeneratorHistory());
        }
    }

    public List<GeneratorHistory> getAllLazyGeneratorHistory() {
        try {
            List<GeneratorHistory> resultGeneratorHistoryList;
            GeneratorHistoryDao generatorHistoryDao = DaoFactory.getGeneratorHistoryDao();
            resultGeneratorHistoryList = generatorHistoryDao.getAllLazyGeneratorHistory();
            return resultGeneratorHistoryList;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get all lazy generator history", ex);
            throw new GeneratorHistoryException("Error in get all lazy generator history");
        }
    }


}
