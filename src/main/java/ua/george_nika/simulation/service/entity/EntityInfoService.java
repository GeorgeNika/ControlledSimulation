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

/**
 * Created by george on 09.12.2015.
 */

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
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get entity info by entity type - " + entityType +
                    " id - " + idEntityInfo, ex);
            throw new EntityInfoException("Error in get entity info by entity type - " + entityType +
                    " id - " + idEntityInfo);
        }
    }

    public EntityInfo getEntityInfoByTypeById(String entityInfoType, int idEntityInfo) {
        try {
            entityInfoExtraDao = DaoFactory.getEntityInfoDaoByType(entityInfoType);
            EntityInfo resultEInfo = entityInfoExtraDao.getEntityInfoByTypeById(entityInfoType, idEntityInfo);
            return resultEInfo;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get entity info by type - " + entityInfoType +
                    " id - " + idEntityInfo, ex);
            throw new EntityInfoException("Error in get entity info by type - " + entityInfoType +
                    " id - " + idEntityInfo);
        }
    }

    public List<EntityInfo> getEntityInfoListByEntityType(String entityType) {
        try {
            Entity tempEntity = EntityFactory.getEntityByType(entityType);
            String entityInfoType = tempEntity.getEntityInfoType();
            return getEntityInfoListByType(entityInfoType);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get entity info list by entity type - " + entityType, ex);
            throw new EntityInfoException("Error in get entity info list by entity type - " + entityType);
        }
    }

    public List<EntityInfo> getEntityInfoListByType(String entityInfoType) {
        try {
            entityInfoExtraDao = DaoFactory.getEntityInfoDaoByType(entityInfoType);
            List<EntityInfo> resultEntityInfoList = entityInfoExtraDao.getAllEntityInfoListByType(entityInfoType);
            return resultEntityInfoList;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get entity info list by type - " + entityInfoType, ex);
            throw new EntityInfoException("Error in get entity info list by type - " + entityInfoType);
        }
    }

    public void createEntityInfoByType(String entityInfoType) {
        try {
            entityInfoExtraDao = DaoFactory.getEntityInfoDaoByType(entityInfoType);
            entityInfoExtraDao.createNewEntityInfo();
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in create entity info by type - " + entityInfoType, ex);
            throw new EntityInfoException("Error in create entity info by type - " + entityInfoType);
        }
    }

    public void deleteEntityInfoByTypeById(String entityInfoType, int idEntityInfo) {
        try {
            entityInfoExtraDao = DaoFactory.getEntityInfoDaoByType(entityInfoType);
            entityInfoExtraDao.deleteEntityInfoById(idEntityInfo);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in delete entity info by type - " + entityInfoType +
                    " id - " + idEntityInfo, ex);
            throw new EntityInfoException("Error in delete entity info by type - " + entityInfoType +
                    " id - " + idEntityInfo);
        }
    }

    public void updateEntityInfoByTypeById(String entityInfoType, EntityInfo entityInfo) {
        try {
            entityInfoExtraDao = DaoFactory.getEntityInfoDaoByType(entityInfoType);
            entityInfoExtraDao.updateEntityInfo(entityInfo);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in update entity info by type - " + entityInfoType +
                    " id - " + entityInfo.getIdEntityInfo(), ex);
            throw new EntityInfoException("Error in update entity info by type - " + entityInfoType +
                    " id - " + entityInfo.getIdEntityInfo());
        }
    }
}
