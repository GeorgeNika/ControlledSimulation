package ua.george_nika.simulation.dao.entity.abstr;

import ua.george_nika.simulation.dao.AbstractDao;
import ua.george_nika.simulation.dao.DaoConst;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.entity.EntityHistoryDao;
import ua.george_nika.simulation.dao.filter.EntityFilter;
import ua.george_nika.simulation.model.entity.EntityHistory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 05.01.2016.
 */
abstract public class AbstractEntityHistoryDao extends AbstractDao implements EntityHistoryDao {

    abstract protected EntityHistory getEntityHistoryFromData(List<Object> dataList);

    abstract protected List<Object> getDataFromEntityHistory(EntityHistory entityHistory);


    @Override
    public int createNewEntityHistoryRecord(EntityHistory entityHistory) {
        int resultId = createEmptyRecordWithOneFieldAndGetNewId(
                DaoConst.ENTITY_TYPE_IN_ENTITY_HISTORY_MAIN_TABLE, entityHistory.getEntityType(), TypeOfFiled.STRING);
        return resultId;
    }

    @Override
    public void saveEntityHistory(EntityHistory entityHistory) {
        List<Object> resultData = getDataFromEntityHistory(entityHistory);
        updateRecordDataById(entityHistory.getIdEntityHistory(),resultData);
    }

    public EntityHistory getLazyEntityHistoryById(int id) {
        List<Object> resultData = getSingleRecordDataById(id);
        EntityHistory resultEntityHistory = getEntityHistoryFromData(resultData);
        return resultEntityHistory;
    }

    public List<EntityHistory> getAllLazyEntityHistory() {
        List<EntityHistory> resultEntityHistoryList = new ArrayList<>();
        List<List<Object>> resultDataListList = getAllRecordDataList();
        EntityHistory tempEntityHistory;
        for (List<Object> loopData : resultDataListList) {
            tempEntityHistory = getEntityHistoryFromData(loopData);
            resultEntityHistoryList.add(tempEntityHistory);
        }
        return resultEntityHistoryList;
    }

    public List<EntityHistory> getLazyEntityHistoryListByFilter(EntityFilter entityFilter) {
        List<EntityHistory> resultEntityHistoryList = new ArrayList<>();
        List<List<Object>> resultDataListList = getRecordDataListByFilter(entityFilter);
        EntityHistory tempEntityHistory;
        for (List<Object> loopData : resultDataListList) {
            tempEntityHistory = getEntityHistoryFromData(loopData);
            resultEntityHistoryList.add(tempEntityHistory);
        }
        return resultEntityHistoryList;
    }

}
