package ua.george_nika.simulation.dao.experiment.abstr;

import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.AbstractDao;
import ua.george_nika.simulation.dao.experiment.ExperimentDao;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.error.SQLDaoException;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.AppConst;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 06.12.2015.
 */
abstract public class AbstractExperimentDao extends AbstractDao implements ExperimentDao {

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = AbstractExperimentDao.class.getSimpleName();

    abstract protected Experiment getExperimentFromData(List<Object> dataList);

    abstract protected List<Object> getDataFromExperiment(Experiment experiment);

    public Experiment getLazyExperimentById(int id) {
        List<Object> resultData = getSingleRecordDataById(id);
        Experiment resultExperiment = getExperimentFromData(resultData);
        return resultExperiment;
    }

    public int createEmptyExperimentByTypeAndGetNewId(String experimentType) {
        int resultId = createEmptyRecordWithOneFieldAndGetNewId(
                AppConst.EXP_TYPE_IN_EXP_MAIN_TABLE, experimentType, TypeOfFiled.STRING);
        return resultId;
    }

    public void changeExperimentType(int idExperiment, String newExperimentType) {
        setValueToFieldById(AppConst.EXP_TYPE_IN_EXP_MAIN_TABLE, newExperimentType, TypeOfFiled.STRING, idExperiment);
    }

    public void updateLazyExperiment(Experiment experiment) {
        List<Object> resultData = getDataFromExperiment(experiment);
        updateRecordDataById(experiment.getIdExperiment(), resultData);
    }

    public void deleteLazyExperimentById(int id) {
        deleteRecordById(id);
    }

    public List<Experiment> getAllLazyExperiment() {
        List<Experiment> resultExperimentList = new ArrayList<Experiment>();
        List<List<Object>> resultDataListList = getAllRecordDataList();
        Experiment tempExperiment;
        for (List<Object> loopData : resultDataListList) {
            tempExperiment = getExperimentFromData(loopData);
            resultExperimentList.add(tempExperiment);
        }
        return resultExperimentList;
    }

    public void addGeneratorToExperimentList(int idExperiment, int idGenerator) {
        String sql = "INSERT INTO exp_gen_manytomany  (id_experiment, id_generator) VALUES (? , ?) ;";

        Connection conn = null;
        PreparedStatement pStatement = null;
        try {
            conn = DaoFactory.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setInt(1, idExperiment);
            pStatement.setInt(2, idGenerator);

            pStatement.executeUpdate();
        } catch (SQLException e) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, " SQL execute error : " + sql + " ;", e);
            throw new SQLDaoException(" SQL execute error : " + sql + " ;", e);
        } finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                AppLog.error(LOGGER_NAME, CLASS_NAME, " SQL close error : " + sql + " ;", e);
                throw new SQLDaoException(" SQL close error : " + sql + " ;", e);
            }
        }
    }

    public void deleteGeneratorFromExperimentList(int idExperiment, int idGenerator) {
        String sql = "DELETE FROM exp_gen_manytomany WHERE id_experiment = ? AND id_generator = ? ; ";

        Connection conn = null;
        PreparedStatement pStatement = null;
        try {
            conn = DaoFactory.getConnection();

            pStatement = conn.prepareStatement(sql);
            pStatement.setInt(1, idExperiment);
            pStatement.setInt(2, idGenerator);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, " SQL execute error : " + sql + " ;", e);
            throw new SQLDaoException(" SQL execute error : " + sql + " ;", e);
        } finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                AppLog.error(LOGGER_NAME, CLASS_NAME, " SQL close error : " + sql + " ;", e);
                throw new SQLDaoException(" SQL close error : " + sql + " ;", e);
            }
        }
    }

}
