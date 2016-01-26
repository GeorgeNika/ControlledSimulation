/**
 * Base methods for work with entity history common table
 */

package ua.george_nika.simulation.dao.entity.abstr;

import ua.george_nika.simulation.dao.AbstractDao;
import ua.george_nika.simulation.dao.DaoConst;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.entity.EntityHistoryDao;
import ua.george_nika.simulation.dao.filter.EntityFilter;
import ua.george_nika.simulation.model.entity.EntityHistory;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractEntityHistoryDao extends AbstractDao implements EntityHistoryDao {

    abstract protected EntityHistory getEntityHistoryFromData(List<Object> dataList);

    abstract protected List<Object> getDataFromEntityHistory(EntityHistory entityHistory);


    @Override
    public int createNewEntityHistoryRecord(EntityHistory entityHistory) {
        return createEmptyRecordWithOneFieldAndGetNewId(DaoConst.ENTITY_TYPE_IN_ENTITY_HISTORY_MAIN_TABLE,
                entityHistory.getEntityType(), TypeOfFiled.STRING);
    }

    @Override
    public void saveEntityHistory(EntityHistory entityHistory) {
        List<Object> resultData = getDataFromEntityHistory(entityHistory);
        updateRecordDataById(entityHistory.getIdEntityHistory(), resultData);
    }

    public EntityHistory getLazyEntityHistoryById(int id) {
        List<Object> resultData = getSingleRecordDataById(id);
        return getEntityHistoryFromData(resultData);
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
