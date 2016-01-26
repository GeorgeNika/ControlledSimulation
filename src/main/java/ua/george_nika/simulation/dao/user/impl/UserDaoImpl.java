/**
 * Work with table in witch stored user`s login and password
 */

package ua.george_nika.simulation.dao.user.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoConnection;
import ua.george_nika.simulation.dao.DaoConst;
import ua.george_nika.simulation.dao.experiment.impl.ExperimentSimpleDao;
import ua.george_nika.simulation.dao.user.UserDao;
import ua.george_nika.simulation.dao.error.SQLDaoException;
import ua.george_nika.simulation.util.AppLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

@Repository
public class UserDaoImpl implements UserDao {

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = ExperimentSimpleDao.class.getSimpleName();

    @Override
    public boolean isExist(String userName, String password) {
        String sql = "SELECT user_name, user_password FROM  user_table WHERE user_name = ? ;";

        Connection conn = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            conn = DaoConnection.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setString(1, userName);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                if (password.equals(resultSet.getString(DaoConst.FIELD_USER_PASSWORD_IN_USER_TABLE))) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new SQLDaoException(LOGGER_NAME, CLASS_NAME, " SQL execute error : " + sql + " ;", e);
        } finally {
            closeResultSet(resultSet, sql);
            closePreparedStatement(pStatement, sql);
            closeConnection(conn, sql);
        }
    }

    protected void closeResultSet(ResultSet resultSet, String sql) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new SQLDaoException(LOGGER_NAME, CLASS_NAME, " SQL close error : " + sql + " ;", e);
        }
    }

    protected void closePreparedStatement(PreparedStatement preparedStatement, String sql) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new SQLDaoException(LOGGER_NAME, CLASS_NAME, " SQL close error : " + sql + " ;", e);
        }
    }

    protected void closeConnection(Connection connection, String sql) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new SQLDaoException(LOGGER_NAME, CLASS_NAME, " SQL close error : " + sql + " ;", e);
        }
    }
}
