package ua.george_nika.simulation.dao.entity.abstr;

import ua.george_nika.simulation.dao.AbstractDao;
import ua.george_nika.simulation.dao.entity.EntityHistoryExtraDao;
import ua.george_nika.simulation.model.entity.EntityHistory;

import java.util.List;

/**
 * Created by george on 14.01.2016.
 */
abstract public class AbstractEntityHistoryExtraDao extends AbstractDao implements EntityHistoryExtraDao {


    abstract protected void fillExtraDataInEntityHistory(List<Object> dataList, EntityHistory entityHistory);

    abstract protected List<Object> getExtraDataFromEntityHistory(EntityHistory entityHistory);


    @Override
    public void createNewEntityHistoryExtraRecordById(int idEntityHistory) {
        createEmptyRecordWithId(idEntityHistory);
    }

    @Override
    public void saveEntityHistoryExtraData(EntityHistory entityHistory) {
        List<Object> resultData = getExtraDataFromEntityHistory(entityHistory);
        updateRecordDataById(entityHistory.getIdEntityHistory(), resultData);
    }

    @Override
    public void addExtraDataToEntityHistory(EntityHistory entityHistory) {
        List<Object> resultData = getSingleRecordDataById(entityHistory.getIdEntityHistory());
        fillExtraDataInEntityHistory(resultData, entityHistory);
    }
}
