/**
 * Base methods for work with experiment history extra tables
 */

package ua.george_nika.simulation.dao.experiment.abstr;

import ua.george_nika.simulation.dao.AbstractDao;
import ua.george_nika.simulation.dao.experiment.ExperimentHistoryExtraDao;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;

import java.util.List;

abstract public class AbstractExperimentHistoryExtraDao extends AbstractDao implements ExperimentHistoryExtraDao {

    abstract protected void fillExtraDataInExperimentHistory(List<Object> dataList, ExperimentHistory experimentHistory);

    abstract protected List<Object> getExtraDataFromExperimentHistory(ExperimentHistory experimentHistory);

    @Override
    public void createNewExperimentHistoryExtraRecordById(int idExperimentHistory) {
        createEmptyRecordWithId(idExperimentHistory);
    }

    @Override
    public void saveExperimentHistoryExtraData(ExperimentHistory experimentHistory) {
        List<Object> resultData = getExtraDataFromExperimentHistory(experimentHistory);
        updateRecordDataById(experimentHistory.getIdExperimentHistory(), resultData);
    }

    public void addExtraDataToExperimentHistory(ExperimentHistory experimentHistory) {
        List<Object> resultData = getSingleRecordDataById(experimentHistory.getIdExperimentHistory());
        fillExtraDataInExperimentHistory(resultData, experimentHistory);
    }
}
