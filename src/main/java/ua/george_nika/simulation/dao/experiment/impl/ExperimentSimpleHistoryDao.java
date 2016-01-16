package ua.george_nika.simulation.dao.experiment.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.error.WrongDaoSettingException;
import ua.george_nika.simulation.dao.experiment.abstr.AbstractExperimentHistoryExtraDao;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.model.experiment.impl.SimpleExperiment;
import ua.george_nika.simulation.util.AppLog;

import java.util.List;

/**
 * Created by george on 04.12.2015.
 */
@Repository
public class ExperimentSimpleHistoryDao extends AbstractExperimentHistoryExtraDao {

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = ExperimentSimpleHistoryDao.class.getSimpleName();

    static {
        DaoFactory.registerExperimentHistoryExtraClassInFactory(SimpleExperiment.EXPERIMENT_TYPE,
                ExperimentSimpleHistoryDao.class.getCanonicalName());
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
        AppLog.error(LOGGER_NAME, CLASS_NAME, "Wrong setting in - " + CLASS_NAME);
        throw new WrongDaoSettingException("Wrong setting in - " + CLASS_NAME);
    }

    protected String getIdName() {
        AppLog.error(LOGGER_NAME, CLASS_NAME, "Wrong setting in - " + CLASS_NAME);
        throw new WrongDaoSettingException("Wrong setting in - " + CLASS_NAME);
    }

    protected int getQuantityOfFields() {
        AppLog.error(LOGGER_NAME, CLASS_NAME, "Wrong setting in - " + CLASS_NAME);
        throw new WrongDaoSettingException("Wrong setting in - " + CLASS_NAME);
    }

    @Override
    protected void fillExtraDataInExperimentHistory(List<Object> dataList, ExperimentHistory experimentHistory) {
        AppLog.error(LOGGER_NAME, CLASS_NAME, "Wrong setting in - " + CLASS_NAME);
        throw new WrongDaoSettingException("Wrong setting in - " + CLASS_NAME);
    }

    @Override
    protected List<Object> getExtraDataFromExperimentHistory(ExperimentHistory experimentHistory) {
        AppLog.error(LOGGER_NAME, CLASS_NAME, "Wrong setting in - " + CLASS_NAME);
        throw new WrongDaoSettingException("Wrong setting in - " + CLASS_NAME);
    }
}
