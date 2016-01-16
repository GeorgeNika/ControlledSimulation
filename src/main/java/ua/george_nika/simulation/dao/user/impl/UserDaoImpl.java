package ua.george_nika.simulation.dao.user.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.experiment.impl.ExperimentSimpleDao;
import ua.george_nika.simulation.dao.user.UserDao;
import ua.george_nika.simulation.dao.error.SQLDaoException;
import ua.george_nika.simulation.util.AppLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by george on 30.12.2015.
 */
@Repository
public class UserDaoImpl implements UserDao{

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = ExperimentSimpleDao.class.getSimpleName();

    @Override
    public boolean isExist(String userName, String password) {
        String sql = "SELECT user_name, user_password FROM  user_table WHERE user_name = ? ;";

        Connection conn = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            conn = DaoFactory.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setString(1, userName);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                if (password.equals(resultSet.getString("user_password"))){
                    return true;
                }
            }
            return false;
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
    }
}
