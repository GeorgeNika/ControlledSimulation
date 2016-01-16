package ua.george_nika.simulation.service.entity;

import org.springframework.stereotype.Service;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.entity.EntityHistoryExtraDao;
import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.entity.EntityHistory;
import ua.george_nika.simulation.model.entity.EntityHistoryFactory;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.service.error.GeneratorHistoryException;
import ua.george_nika.simulation.util.AppLog;

/**
 * Created by george on 10.01.2016.
 */
@Service
public class EntityHistoryService {
    private static String LOGGER_NAME = AppLog.SERVICE;
    private static String CLASS_NAME = EntityHistoryService.class.getName();


    public static EntityHistory getNewEntityHistory(Generator generator, Entity entity) {
        try {
            //create new history
            EntityHistory entityHistory
                    = EntityHistoryFactory.getEntityHistoryByType(generator.getEntityType());

            //set data from generator and entity
            entityHistory.setInitialEntityHistoryData(generator, entity);

            //create record in common history db


            //create record in extra history db
            EntityHistoryExtraDao entityHistoryExtraDao
                    = DaoFactory.getEntityHistoryExtraDaoByType(entityHistory.getEntityType());
            int idEntityHistory = entityHistoryExtraDao.createNewEntityHistoryExtraRecordAndGetId();
            entityHistory.setIdEntityHistory(idEntityHistory);

            //first save in db
            saveEntityHistory(entityHistory);

            return entityHistory;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in creating entity history for generator id - " +
                    generator.getIdGenerator() + " and entity type - " + entity.getEntityType(), ex);
            throw new GeneratorHistoryException("Error in creating entity history for generator id - " +
                    generator.getIdGenerator() + " and entity type - " + entity.getEntityType());
        }
    }

    public static void saveEntityHistory(EntityHistory entityHistory) {
        try {
            EntityHistoryExtraDao entityHistoryExtraDao
                    = DaoFactory.getEntityHistoryExtraDaoByType(entityHistory.getEntityType());
            entityHistoryExtraDao.saveEntityHistoryExtraData(entityHistory);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in saving data for entity history id - " +
                    entityHistory.getIdEntityHistory(), ex);
            throw new GeneratorHistoryException("Error in saving data for entity history id - " +
                    entityHistory.getIdEntityHistory());
        }
    }
}
