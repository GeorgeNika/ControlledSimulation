/**
 * Work with entity info extra tables
 */

package ua.george_nika.simulation.dao.entity;

import ua.george_nika.simulation.model.entity.EntityInfo;

import java.util.List;

@SuppressWarnings("unused")

public interface EntityInfoExtraDao {

    void addExtraDataToEntityInfo(EntityInfo entityInfo);

    EntityInfo getEntityInfoByTypeById(String entityInfoType, int idEntityInfo);

    List<EntityInfo> getAllEntityInfoListByType(String entityInfoType);

    void createNewEntityInfo();

    void deleteEntityInfoById(int idEntityInfo);

    void updateEntityInfo(EntityInfo entityInfo);

}
