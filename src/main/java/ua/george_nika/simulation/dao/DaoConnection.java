/**
 * Prepare database connection
 */

package ua.george_nika.simulation.dao;

import ua.george_nika.simulation.dao.error.ConnectionDaoException;
import ua.george_nika.simulation.util.AppLog;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SuppressWarnings("unused")

public class DaoConnection {
    private static final String LOGGER_NAME = AppLog.DAO;
    private static final String CLASS_NAME = DaoConnection.class.getSimpleName();

    private static DataSource dataSource;

    private static void setDataBaseConnection(String jndiName) {
        // run from dispatcher-servlet.xml
        String localJndiName = jndiName.replace(" ", "").replace("\n", "");

        InitialContext context;
        try {
            context = new InitialContext();
        } catch (NamingException e) {
            throw new ConnectionDaoException(LOGGER_NAME, CLASS_NAME, "Can't obtain InitialContext or Lookup", e);
        }

        try {
            dataSource = (DataSource) context.lookup(localJndiName);
        } catch (NamingException e) {
            throw new ConnectionDaoException(LOGGER_NAME, CLASS_NAME, "Can't lookup " + jndiName, e);
        }
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new ConnectionDaoException(LOGGER_NAME, CLASS_NAME, "Can't obtain connection", e);
        }
    }
}
