package ua.george_nika.simulation.dao.entity;

import ua.george_nika.simulation.model.entity.EntityInfo;

import java.util.List;


/**
 * Created by george on 08.12.2015.
 */
public interface EntityInfoExtraDao {

    void addExtraDataToEntityInfo(EntityInfo entityInfo);

    EntityInfo getEntityInfoByTypeById(String entityInfoType, int idEntityInfo);

    List<EntityInfo> getAllEntityInfoListByType(String entityInfoType);

    void createNewEntityInfo();

    void deleteEntityInfoById(int idEntityInfo);

    void updateEntityInfo(EntityInfo entityInfo);

}
