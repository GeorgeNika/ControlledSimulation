package ua.george_nika.simulation.dao.generator.abstr;

import ua.george_nika.simulation.dao.AbstractDao;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.generator.GeneratorDao;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.error.SQLDaoException;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.AppConst;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 06.12.2015.
 */
abstract public class AbstractGeneratorDao extends AbstractDao implements GeneratorDao {

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = AbstractGeneratorDao.class.getSimpleName();

    abstract protected Generator getGeneratorFromData(List<Object> dataList);

    abstract protected List<Object> getDataFromGenerator(Generator generator);

    public Generator getLazyGeneratorById(int id) {
        List<Object> resultData = getSingleRecordDataById(id);
        Generator resultGenerator = getGeneratorFromData(resultData);
        return resultGenerator;
    }

    public int createdEmptyGeneratorByTypeAndGetNewId(String generatorType) {
        int resultId = createEmptyRecordWithOneFieldAndGetNewId(
                AppConst.GEN_TYPE_IN_GEN_MAIN_TABLE, generatorType, TypeOfFiled.STRING);
        return resultId;
    }

    public void updateLazyGenerator(Generator generator) {
        List<Object> resultData = getDataFromGenerator(generator);
        updateRecordDataById(generator.getIdGenerator(), resultData);
    }

    public void deleteLazyGeneratorById(int id) {
        deleteRecordById(id);
    }

    public List<Generator> getAllLazyGenerator() {
        List<Generator> resultGeneratorList = new ArrayList<Generator>();
        List<List<Object>> resultDataListList = getAllRecordDataList();
        Generator tempGenerator;
        for (List<Object> loopData : resultDataListList) {
            tempGenerator = getGeneratorFromData(loopData);
            resultGeneratorList.add(tempGenerator);
        }
        return resultGeneratorList;
    }


    public List<Generator> getAllLazyGeneratorByExperimentId(int idExperiment) {
        List<Generator> resultGeneratorList = new ArrayList<Generator>();
        List<List<Object>> resultDataListList = getAllGeneratorDataListByExperimentId(idExperiment);
        Generator tempGenerator;
        for (List<Object> loopData : resultDataListList) {
            tempGenerator = getGeneratorFromData(loopData);
            resultGeneratorList.add(tempGenerator);
        }
        return resultGeneratorList;
    }

    protected List<List<Object>> getAllGeneratorDataListByExperimentId(int idExperiment) {
        String sql = "SELECT " + getTableName() + ".* FROM " + getTableName() + ",exp_gen_manytomany EG " +
                "WHERE EG.id_experiment = ? " +
                "AND EG.id_generator = " + getTableName() + "." + getIdName() + " ;";

        List<List<Object>> resultDataListList = new ArrayList<List<Object>>();
        Connection conn = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            conn = DaoFactory.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setInt(1, idExperiment);
            resultSet = pStatement.executeQuery();
            List<Object> resultDataList;
            while (resultSet.next()) {
                resultDataList = new ArrayList<Object>();
                // fields in jdbc starts from 1 instead 0
                for (int i = 1; i <= getQuantityOfFields(); i++) {
                    resultDataList.add(getDataFromResultSet(i, resultSet));
                }
                resultDataListList.add(resultDataList);
            }
        } catch (SQLException e) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, " SQL execute error : " + sql + " ;", e);
            throw new SQLDaoException(" SQL execute error : " + sql + " ;", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
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
        return resultDataListList;
    }
}
