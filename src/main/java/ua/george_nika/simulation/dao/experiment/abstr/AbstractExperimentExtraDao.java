/**
 * Base methods for work with experiment extra tables
 */

package ua.george_nika.simulation.dao.experiment.abstr;

import ua.george_nika.simulation.dao.AbstractDao;
import ua.george_nika.simulation.dao.experiment.ExperimentExtraDao;
import ua.george_nika.simulation.model.experiment.Experiment;

import java.util.List;

abstract public class AbstractExperimentExtraDao extends AbstractDao implements ExperimentExtraDao {

    abstract protected void fillExtraDataInExperiment(List<Object> dataList, Experiment experiment);

    abstract protected List<Object> getExtraDataFromExperiment(Experiment experiment);

    public void addExtraDataToExperiment(Experiment experiment) {
        List<Object> resultData = getSingleRecordDataById(experiment.getIdExperiment());
        fillExtraDataInExperiment(resultData, experiment);
    }

    public void createNewExperimentExtraDataById(int idExperiment) {
        createEmptyRecordWithId(idExperiment);
    }

    public void updateExperimentExtraData(Experiment experiment) {
        List<Object> resultData = getExtraDataFromExperiment(experiment);
        updateRecordDataById(experiment.getIdExperiment(), resultData);
    }

    public void deleteExperimentExtraData(int idExperiment) {
        deleteRecordById(idExperiment);
    }

}
