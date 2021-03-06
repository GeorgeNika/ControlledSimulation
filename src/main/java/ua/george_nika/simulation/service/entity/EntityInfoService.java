/**
 * Common service for entity info
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.service.entity;

import org.springframework.stereotype.Service;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.entity.EntityInfoExtraDao;
import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.entity.EntityFactory;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.service.error.EntityInfoException;
import ua.george_nika.simulation.util.AppLog;

import java.util.List;

@Service
public class EntityInfoService {
    private static String LOGGER_NAME = AppLog.SERVICE;
    private static String CLASS_NAME = EntityInfoService.class.getName();

    EntityInfoExtraDao entityInfoExtraDao;

    public EntityInfo getEntityInfoByEntityTypeById(String entityType, int idEntityInfo) {
        try {
            Entity tempEntity = EntityFactory.getEntityByType(entityType);
            String entityInfoType = tempEntity.getEntityInfoType();
            return getEntityInfoByTypeById(entityInfoType, idEntityInfo);
        } catch (RuntimeException ex) {
            throw new EntityInfoException(LOGGER_NAME, CLASS_NAME, "Error in get entity info by entity type - "
                    + entityType + " id - " + idEntityInfo, ex);
        }
    }

    public EntityInfo getEntityInfoByTypeById(String entityInfoType, int idEntityInfo) {
        try {
            entityInfoExtraDao = DaoFactory.getEntityInfoDaoByType(entityInfoType);
            return entityInfoExtraDao.getEntityInfoByTypeById(entityInfoType, idEntityInfo);
        } catch (RuntimeException ex) {
            throw new EntityInfoException(LOGGER_NAME, CLASS_NAME, "Error in get entity info by type - "
                    + entityInfoType + " id - " + idEntityInfo, ex);
        }
    }

    public List<EntityInfo> getEntityInfoListByEntityType(String entityType) {
        try {
            Entity tempEntity = EntityFactory.getEntityByType(entityType);
            String entityInfoType = tempEntity.getEntityInfoType();
            return getEntityInfoListByType(entityInfoType);
        } catch (RuntimeException ex) {
            throw new EntityInfoException(LOGGER_NAME, CLASS_NAME,
                    "Error in get entity info list by entity type - " + entityType, ex);
        }
    }

    public List<EntityInfo> getEntityInfoListByType(String entityInfoType) {
        try {
            entityInfoExtraDao = DaoFactory.getEntityInfoDaoByType(entityInfoType);
            return entityInfoExtraDao.getAllEntityInfoListByType(entityInfoType);
        } catch (RuntimeException ex) {
            throw new EntityInfoException(LOGGER_NAME, CLASS_NAME,
                    "Error in get entity info list by type - " + entityInfoType, ex);
        }
    }

    public void createEntityInfoByType(String entityInfoType) {
        try {
            entityInfoExtraDao = DaoFactory.getEntityInfoDaoByType(entityInfoType);
            entityInfoExtraDao.createNewEntityInfo();
        } catch (RuntimeException ex) {
            throw new EntityInfoException(LOGGER_NAME, CLASS_NAME, "Error in create entity info by type - "
                    + entityInfoType, ex);
        }
    }

    public void deleteEntityInfoByTypeById(String entityInfoType, int idEntityInfo) {
        try {
            entityInfoExtraDao = DaoFactory.getEntityInfoDaoByType(entityInfoType);
            entityInfoExtraDao.deleteEntityInfoById(idEntityInfo);
        } catch (RuntimeException ex) {
            throw new EntityInfoException(LOGGER_NAME, CLASS_NAME, "Error in delete entity info by type - "
                    + entityInfoType + " id - " + idEntityInfo, ex);
        }
    }

    public void updateEntityInfoByTypeById(String entityInfoType, EntityInfo entityInfo) {
        try {
            entityInfoExtraDao = DaoFactory.getEntityInfoDaoByType(entityInfoType);
            entityInfoExtraDao.updateEntityInfo(entityInfo);
        } catch (RuntimeException ex) {
            throw new EntityInfoException(LOGGER_NAME, CLASS_NAME, "Error in update entity info by type - "
                    + entityInfoType + " id - " + entityInfo.getIdEntityInfo(), ex);
        }
    }
}
