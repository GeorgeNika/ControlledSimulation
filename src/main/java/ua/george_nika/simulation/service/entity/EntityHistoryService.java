/**
 * Common service for entity`s history
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.service.entity;

import org.springframework.stereotype.Service;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.entity.EntityHistoryDao;
import ua.george_nika.simulation.dao.entity.EntityHistoryExtraDao;
import ua.george_nika.simulation.dao.filter.EntityFilter;
import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.entity.EntityHistory;
import ua.george_nika.simulation.model.entity.EntityHistoryFactory;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.service.error.EntityHistoryException;
import ua.george_nika.simulation.util.AppLog;

import java.util.List;

@SuppressWarnings("unused")

@Service
public class EntityHistoryService {
    private static String LOGGER_NAME = AppLog.SERVICE;
    private static String CLASS_NAME = EntityHistoryService.class.getName();


    public static EntityHistory getNewEntityHistory(Generator generator, Entity entity) {
        try {
            //create new history
            EntityHistory entityHistory
                    = EntityHistoryFactory.getEntityHistoryByType(entity.getEntityType());

            //set data from generator and entity
            entityHistory.setInitialEntityHistoryData(generator, entity);

            //create record in common history db
            EntityHistoryDao entityHistoryDao = DaoFactory.getEntityHistoryDao();
            int idEntityHistory = entityHistoryDao.createNewEntityHistoryRecord(entityHistory);
            entityHistory.setIdEntityHistory(idEntityHistory);

            //create record in extra history db
            EntityHistoryExtraDao entityHistoryExtraDao
                    = DaoFactory.getEntityHistoryExtraDaoByType(entityHistory.getEntityType());
            entityHistoryExtraDao.createNewEntityHistoryExtraRecordById(idEntityHistory);

            //first save in db
            saveEntityHistory(entityHistory);

            return entityHistory;
        } catch (RuntimeException ex) {
            throw new EntityHistoryException(LOGGER_NAME, CLASS_NAME,
                    "Error in creating entity history for generator id - " +
                            generator.getIdGenerator() + " and entity type - " + entity.getEntityType(), ex);
        }
    }

    public static void saveEntityHistory(EntityHistory entityHistory) {
        try {
            EntityHistoryDao entityHistoryDao = DaoFactory.getEntityHistoryDao();
            entityHistoryDao.saveEntityHistory(entityHistory);
            EntityHistoryExtraDao entityHistoryExtraDao
                    = DaoFactory.getEntityHistoryExtraDaoByType(entityHistory.getEntityType());
            entityHistoryExtraDao.saveEntityHistoryExtraData(entityHistory);
        } catch (RuntimeException ex) {
            throw new EntityHistoryException(LOGGER_NAME, CLASS_NAME, "Error in saving data for entity history id - "
                    + entityHistory.getIdEntityHistory(), ex);
        }
    }

    public EntityHistory getEntityHistoryById(int idEntityHistory) {
        try {
            EntityHistory resultEntityHistory = getLazyEntityHistoryById(idEntityHistory);
            EntityHistoryExtraDao entityHistoryExtraDao = DaoFactory.getEntityHistoryExtraDaoByType(
                    resultEntityHistory.getEntityType());
            entityHistoryExtraDao.addExtraDataToEntityHistory(resultEntityHistory);
            return resultEntityHistory;
        } catch (RuntimeException ex) {
            throw new EntityHistoryException(LOGGER_NAME, CLASS_NAME,
                    "Error in get entity history by id history - " + idEntityHistory, ex);
        }
    }

    public EntityHistory getLazyEntityHistoryById(int idEntityHistory) {
        try {
            EntityHistory resultEntityHistory;
            EntityHistoryDao entityHistoryDao = DaoFactory.getEntityHistoryDao();
            resultEntityHistory = entityHistoryDao.getLazyEntityHistoryById(idEntityHistory);
            return resultEntityHistory;
        } catch (RuntimeException ex) {
            throw new EntityHistoryException(LOGGER_NAME, CLASS_NAME,
                    "Error in get lazy entity history by id history - " + idEntityHistory, ex);
        }
    }

    public List<EntityHistory> getAllLazyEntityHistory() {
        try {
            List<EntityHistory> resultEntityHistoryList;
            EntityHistoryDao entityHistoryDao = DaoFactory.getEntityHistoryDao();
            resultEntityHistoryList = entityHistoryDao.getAllLazyEntityHistory();
            return resultEntityHistoryList;
        } catch (RuntimeException ex) {
            throw new EntityHistoryException(LOGGER_NAME, CLASS_NAME, "Error in get all lazy entity history", ex);
        }
    }

    public List<EntityHistory> getLazyEntityHistoryListByFilter(EntityFilter entityFilter) {
        try {
            List<EntityHistory> resultEntityHistoryList;
            EntityHistoryDao entityHistoryDao = DaoFactory.getEntityHistoryDao();
            resultEntityHistoryList = entityHistoryDao.getLazyEntityHistoryListByFilter(entityFilter);
            return resultEntityHistoryList;
        } catch (RuntimeException ex) {
            throw new EntityHistoryException(LOGGER_NAME, CLASS_NAME,
                    "Error in get lazy entity history list by filter", ex);
        }
    }

}
