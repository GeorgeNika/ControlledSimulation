package ua.george_nika.simulation.dao.entity.abstr;

import ua.george_nika.simulation.dao.AbstractDao;
import ua.george_nika.simulation.dao.entity.EntityInfoExtraDao;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.model.entity.EntityInfoFactory;
import ua.george_nika.simulation.util.AppLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 09.12.2015.
 */
abstract public class AbstractEntityInfoExtraDao extends AbstractDao implements EntityInfoExtraDao {

    abstract protected void fillExtraDataInEntityInfo(List<Object> dataList, EntityInfo entityInfo);

    abstract protected List<Object> getExtraDataFromEntityInfo(EntityInfo entityInfo);

    @Override
    public void addExtraDataToEntityInfo(EntityInfo entityInfo){
        List<Object> resultData = getSingleRecordDataById(entityInfo.getIdEntityInfo());
        fillExtraDataInEntityInfo(resultData, entityInfo);
    }

    @Override
    public List<EntityInfo> getAllEntityInfoListByType(String type) {
        List<EntityInfo> resultEntityInfoList = new ArrayList<>();
        List<List<Object>> tempListList = getAllRecordDataList();
        EntityInfo tempEntityInfo;
        for (List<Object> loopData : tempListList) {
            tempEntityInfo = EntityInfoFactory.getEntityInfoByType(type);
            fillExtraDataInEntityInfo(loopData, tempEntityInfo);
            resultEntityInfoList.add(tempEntityInfo);
        }
        return resultEntityInfoList;
    }

    @Override
    public EntityInfo getEntityInfoByTypeById(String entityInfoType, int idEntityInfo){
        EntityInfo resultEntityInfo = EntityInfoFactory.getEntityInfoByType(entityInfoType);
        List<Object> resultData = getSingleRecordDataById(idEntityInfo);
        fillExtraDataInEntityInfo(resultData, resultEntityInfo);
        return resultEntityInfo;
    }

    @Override
    public void createNewEntityInfo() {
        createEmptyRecordAndGetNewId();
    }

    @Override
    public void deleteEntityInfoById(int idEntityInfo) {
        deleteRecordById(idEntityInfo);
    }

    @Override
    public void updateEntityInfo(EntityInfo entityInfo) {
        updateRecordDataById(entityInfo.getIdEntityInfo(),getExtraDataFromEntityInfo(entityInfo));
    }
}
