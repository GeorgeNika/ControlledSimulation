/**
 * work`s with "stop" experiment history table
 */

package ua.george_nika.simulation.dao.experiment.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.error.WrongDaoSettingException;
import ua.george_nika.simulation.dao.experiment.abstr.AbstractExperimentHistoryExtraDao;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.model.experiment.impl.StopExperiment;
import ua.george_nika.simulation.util.AppLog;

import java.util.List;

@Repository
public class ExperimentStopHistoryDao extends AbstractExperimentHistoryExtraDao {

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = ExperimentStopHistoryDao.class.getSimpleName();

    static {
        DaoFactory.registerExperimentHistoryExtraClassInFactory(StopExperiment.EXPERIMENT_TYPE,
                ExperimentStopHistoryDao.class.getCanonicalName());
    }

    @Override
    public void createNewExperimentHistoryExtraRecordById(int idExperimentHistory) {
        // do noting
    }

    @Override
    public void saveExperimentHistoryExtraData(ExperimentHistory experimentHistory) {
        // do nothing
    }

    @Override
    public void addExtraDataToExperimentHistory(ExperimentHistory experimentHistory) {
        // do nothing
    }

    protected String getTableName() {
        throw new WrongDaoSettingException(LOGGER_NAME, CLASS_NAME, "Wrong setting in - " + CLASS_NAME,
                new RuntimeException());
    }

    protected String getIdName() {
        throw new WrongDaoSettingException(LOGGER_NAME, CLASS_NAME, "Wrong setting in - " + CLASS_NAME,
                new RuntimeException());
    }

    protected int getQuantityOfFields() {
        throw new WrongDaoSettingException(LOGGER_NAME, CLASS_NAME, "Wrong setting in - " + CLASS_NAME,
                new RuntimeException());
    }

    @Override
    protected void fillExtraDataInExperimentHistory(List<Object> dataList, ExperimentHistory experimentHistory) {
        throw new WrongDaoSettingException(LOGGER_NAME, CLASS_NAME, "Wrong setting in - " + CLASS_NAME,
                new RuntimeException());
    }

    @Override
    protected List<Object> getExtraDataFromExperimentHistory(ExperimentHistory experimentHistory) {
        throw new WrongDaoSettingException(LOGGER_NAME, CLASS_NAME, "Wrong setting in - " + CLASS_NAME,
                new RuntimeException());
    }
}
