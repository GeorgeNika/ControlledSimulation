/**
 * Base methods for work with experiment history common table
 */

package ua.george_nika.simulation.dao.experiment.abstr;

import ua.george_nika.simulation.dao.AbstractDao;
import ua.george_nika.simulation.dao.DaoConst;
import ua.george_nika.simulation.dao.experiment.ExperimentHistoryDao;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.filter.ExperimentFilter;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractExperimentHistoryDao extends AbstractDao implements ExperimentHistoryDao {

    abstract protected ExperimentHistory getExperimentHistoryFromData(List<Object> dataList);

    abstract protected List<Object> getDataFromExperimentHistory(ExperimentHistory experimentHistory);

    @Override
    public int createNewExperimentHistoryRecord(ExperimentHistory experimentHistory) {
        return createEmptyRecordWithOneFieldAndGetNewId(DaoConst.EXP_TYPE_IN_EXP_HISTORY_MAIN_TABLE,
                experimentHistory.getExperimentType(), TypeOfFiled.STRING);
    }

    @Override
    public void saveExperimentHistory(ExperimentHistory experimentHistory) {
        List<Object> resultData = getDataFromExperimentHistory(experimentHistory);
        updateRecordDataById(experimentHistory.getIdExperimentHistory(), resultData);
    }

    public ExperimentHistory getLazyExperimentHistoryById(int id) {
        List<Object> resultData = getSingleRecordDataById(id);
        return getExperimentHistoryFromData(resultData);
    }

    public List<ExperimentHistory> getAllLazyExperimentHistory() {
        List<ExperimentHistory> resultExperimentHistoryList = new ArrayList<>();
        List<List<Object>> resultDataListList = getAllRecordDataList();
        ExperimentHistory tempExperimentHistory;
        for (List<Object> loopData : resultDataListList) {
            tempExperimentHistory = getExperimentHistoryFromData(loopData);
            resultExperimentHistoryList.add(tempExperimentHistory);
        }
        return resultExperimentHistoryList;
    }

    public List<ExperimentHistory> getLazyExperimentHistoryListByFilter(ExperimentFilter experimentFilter) {
        List<ExperimentHistory> resultExperimentHistoryList = new ArrayList<>();
        List<List<Object>> resultDataListList = getRecordDataListByFilter(experimentFilter);
        ExperimentHistory tempExperimentHistory;
        for (List<Object> loopData : resultDataListList) {
            tempExperimentHistory = getExperimentHistoryFromData(loopData);
            resultExperimentHistoryList.add(tempExperimentHistory);
        }
        return resultExperimentHistoryList;
    }

    public void deleteExperimentHistoryById(int id) {
        deleteRecordById(id);
    }
}
