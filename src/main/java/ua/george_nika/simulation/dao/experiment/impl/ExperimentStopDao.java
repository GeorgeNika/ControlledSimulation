/**
 * work`s with "stop" experiment table
 */

package ua.george_nika.simulation.dao.experiment.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.error.WrongDaoSettingException;
import ua.george_nika.simulation.dao.experiment.abstr.AbstractExperimentExtraDao;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.impl.StopExperiment;
import ua.george_nika.simulation.util.AppLog;

import java.util.List;

@Repository
public class ExperimentStopDao extends AbstractExperimentExtraDao {

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = ExperimentStopDao.class.getSimpleName();

    static {
        DaoFactory.registerExperimentExtraClassInFactory(StopExperiment.EXPERIMENT_TYPE,
                ExperimentStopDao.class.getCanonicalName());
    }

    @Override
    public void addExtraDataToExperiment(Experiment experiment) {
        // do nothing
    }

    @Override
    public void createNewExperimentExtraDataById(int idExperiment) {
        // do nothing
    }

    @Override
    public void updateExperimentExtraData(Experiment experiment) {
        // do nothing
    }

    @Override
    public void deleteExperimentExtraData(int idExperiment) {
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
    protected void fillExtraDataInExperiment(List<Object> dataList, Experiment experiment) {
        throw new WrongDaoSettingException(LOGGER_NAME, CLASS_NAME, "Wrong setting in - " + CLASS_NAME,
                new RuntimeException());
    }


    protected List<Object> getExtraDataFromExperiment(Experiment experiment) {
        throw new WrongDaoSettingException(LOGGER_NAME, CLASS_NAME, "Wrong setting in - " + CLASS_NAME,
                new RuntimeException());
    }
}
