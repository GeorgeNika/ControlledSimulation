package ua.george_nika.simulation.dao.experiment.abstr;

import ua.george_nika.simulation.dao.AbstractDao;
import ua.george_nika.simulation.dao.experiment.ExperimentHistoryDao;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.util.AppConst;
import ua.george_nika.simulation.util.AppLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 05.01.2016.
 */
abstract public class AbstractExperimentHistoryDao extends AbstractDao implements ExperimentHistoryDao {

    abstract protected ExperimentHistory getExperimentHistoryFromData(List<Object> dataList);

    abstract protected List<Object> getDataFromExperimentHistory(ExperimentHistory experimentHistory);

    @Override
    public int createNewExperimentHistoryRecord(ExperimentHistory experimentHistory) {
        int resultId = createEmptyRecordWithOneFieldAndGetNewId(
                AppConst.EXP_TYPE_IN_EXP_HISTORY_MAIN_TABLE, experimentHistory.getExperimentType(), TypeOfFiled.STRING);
        return resultId;
    }

    @Override
    public void saveExperimentHistory(ExperimentHistory experimentHistory) {
        List<Object> resultData = getDataFromExperimentHistory(experimentHistory);
        updateRecordDataById(experimentHistory.getIdExperimentHistory(),resultData);
    }

    public ExperimentHistory getLazyExperimentHistoryById(int id) {
        List<Object> resultData = getSingleRecordDataById(id);
        ExperimentHistory resultExperimentHistory = getExperimentHistoryFromData(resultData);
        return resultExperimentHistory;
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
}
