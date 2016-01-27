/**
 * Base methods for work with database
 */

package ua.george_nika.simulation.dao;

import org.joda.time.DateTime;
import ua.george_nika.simulation.dao.error.*;
import ua.george_nika.simulation.dao.filter.DaoFilter;
import ua.george_nika.simulation.util.AppLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

abstract public class AbstractDao {

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = AbstractDao.class.getSimpleName();

    protected List<TypeOfFiled> fieldTypeInTable = new ArrayList<>();
    protected List<String> fieldNameInTable = new ArrayList<>();


    abstract protected String getTableName();

    abstract protected String getIdName();

    abstract protected int getQuantityOfFields();

    protected TypeOfFiled getFieldTypeInTable(int i) {
        return fieldTypeInTable.get(i);
    }

    protected String getFieldNameInTable(int i) {
        return fieldNameInTable.get(i);
    }

    protected List<Object> getSingleRecordDataById(int id) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + getIdName() + " = ?;";

        List<Object> resultDataList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            conn = DaoConnection.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setInt(1, id);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                // fields in jdbc starts from 1 instead 0
                for (int i = 1; i <= getQuantityOfFields(); i++) {
                    resultDataList.add(getDataFromResultSet(i, resultSet));
                }

            } else {
                throw new NoResultDaoException(LOGGER_NAME, CLASS_NAME, "no result in table " + getTableName() + " ; "
                        + "field - " + getIdName() + " ; value - " + id, new RuntimeException());
            }
            if (resultSet.next()) {
                throw new NotSingleResultDaoException(LOGGER_NAME, CLASS_NAME,
                        "not single result in table " + getTableName() + " ; "
                                + "field - " + getIdName() + " ; value - " + id, new RuntimeException());
            }
        } catch (SQLException e) {
            throw new SQLDaoException(LOGGER_NAME, CLASS_NAME, " SQL execute error : " + sql + " ; id - " + id, e);
        } finally {
            closeResultSet(resultSet, sql);
            closePreparedStatement(pStatement, sql);
            closeConnection(conn, sql);
        }
        return resultDataList;
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

    protected List<List<Object>> getAllRecordDataList() {
        String sql = "SELECT * FROM " + getTableName() + " ORDER BY " + getIdName() + " ;";

        List<List<Object>> resultDataListList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            conn = DaoConnection.getConnection();
            pStatement = conn.prepareStatement(sql);
            resultSet = pStatement.executeQuery();
            List<Object> resultDataList;
            while (resultSet.next()) {
                resultDataList = new ArrayList<>();
                // fields in jdbc starts from 1 instead 0
                for (int i = 1; i <= getQuantityOfFields(); i++) {
                    resultDataList.add(getDataFromResultSet(i, resultSet));
                }
                resultDataListList.add(resultDataList);
            }
        } catch (SQLException e) {
            throw new SQLDaoException(LOGGER_NAME, CLASS_NAME, " SQL execute error : " + sql + " ;", e);
        } finally {
            closeResultSet(resultSet, sql);
            closePreparedStatement(pStatement, sql);
            closeConnection(conn, sql);
        }
        return resultDataListList;
    }

    protected List<List<Object>> getRecordDataListByFilter(DaoFilter daoFilter) {
        List<String> filterFieldNameList = new ArrayList<>();
        List<Integer> filterFieldValueList = new ArrayList<>();
        daoFilter.setFilterInfo(filterFieldNameList, filterFieldValueList);

        StringBuilder tempSql = new StringBuilder();
        tempSql.append("SELECT * FROM ").append(getTableName());
        if (filterFieldNameList.size() > 0) {
            tempSql.append(" WHERE ");
        }
        for (int i = 0; i < filterFieldNameList.size(); i++) {
            tempSql.append(filterFieldNameList.get(i)).append(" = ").append(filterFieldValueList.get(i));
            if (i < filterFieldNameList.size() - 1) {
                tempSql.append(" and ");
            }
        }
        tempSql.append(" ORDER BY ").append(getIdName()).append(" ;");
        String sql = tempSql.toString();

        List<List<Object>> resultDataListList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            conn = DaoConnection.getConnection();
            pStatement = conn.prepareStatement(sql);
            resultSet = pStatement.executeQuery();
            List<Object> resultDataList;
            while (resultSet.next()) {
                resultDataList = new ArrayList<>();
                // fields in jdbc starts from 1 instead 0
                for (int i = 1; i <= getQuantityOfFields(); i++) {
                    resultDataList.add(getDataFromResultSet(i, resultSet));
                }
                resultDataListList.add(resultDataList);
            }
        } catch (SQLException e) {
            throw new SQLDaoException(LOGGER_NAME, CLASS_NAME, " SQL execute error : " + sql + " ;", e);
        } finally {
            closeResultSet(resultSet, sql);
            closePreparedStatement(pStatement, sql);
            closeConnection(conn, sql);
        }
        return resultDataListList;
    }

    protected List<List<Object>> getAllRecordDataListByIntField(String field, int value) {
        String sql = "SELECT * FROM " + getTableName() + " " +
                "WHERE " + field + "=? " + " ORDER BY " + getIdName() + " ;";

        List<List<Object>> resultDataListList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            conn = DaoConnection.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setInt(1, value);
            resultSet = pStatement.executeQuery();
            List<Object> resultDataList;
            while (resultSet.next()) {
                resultDataList = new ArrayList<>();
                // fields in jdbc starts from 1 instead 0
                for (int i = 1; i <= getQuantityOfFields(); i++) {
                    resultDataList.add(getDataFromResultSet(i, resultSet));
                }
                resultDataListList.add(resultDataList);
            }
        } catch (SQLException e) {
            throw new SQLDaoException(LOGGER_NAME, CLASS_NAME, " SQL execute error : " + sql + " ;", e);
        } finally {
            closeResultSet(resultSet, sql);
            closePreparedStatement(pStatement, sql);
            closeConnection(conn, sql);
        }
        return resultDataListList;
    }

    protected void updateRecordDataById(int id, List<Object> dataList) {
        StringBuilder tempSql = new StringBuilder();
        tempSql.append("UPDATE ").append(getTableName()).append(" SET ");
        for (int i = 2; i <= getQuantityOfFields(); i++) {
            tempSql.append(getNameOfField(i)).append(" = ?");
            if (i < getQuantityOfFields()) {
                tempSql.append(",");
            }
        }
        tempSql.append(" WHERE ").append(getIdName()).append("= ?;");
        String sql = tempSql.toString();

        Connection conn = null;
        PreparedStatement pStatement = null;
        try {
            conn = DaoConnection.getConnection();
            pStatement = conn.prepareStatement(sql);
            for (int i = 1; i < getQuantityOfFields(); i++) {
                setDataToPrepareStatement(pStatement, i, getTypeOfField(i + 1), dataList.get(i));
            }
            setDataToPrepareStatement(pStatement, getQuantityOfFields(), getTypeOfField(1), id); // set ID

            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLDaoException(LOGGER_NAME, CLASS_NAME, " SQL execute error : " + sql + " ;", e);
        } finally {
            closePreparedStatement(pStatement, sql);
            closeConnection(conn, sql);
        }
    }

    protected int createEmptyRecordAndGetNewId() {
        String sql = "INSERT INTO " + getTableName() + " DEFAULT VALUES;";

        int resultId = 0;
        Connection conn = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            conn = DaoConnection.getConnection();
            pStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pStatement.executeUpdate();
            resultSet = pStatement.getGeneratedKeys();
            if (resultSet.next()) {
                resultId = resultSet.getInt(getIdName());
            }
        } catch (SQLException e) {
            throw new SQLDaoException(LOGGER_NAME, CLASS_NAME, " SQL close error : " + sql + " ; id - " + resultId, e);
        } finally {
            closeResultSet(resultSet, sql);
            closePreparedStatement(pStatement, sql);
            closeConnection(conn, sql);
        }
        return resultId;
    }

    protected void createEmptyRecordWithId(int id) {
        String sql = "INSERT INTO " + getTableName() + " ( " + getIdName() + " ) VALUES (?);";

        Connection conn = null;
        PreparedStatement pStatement = null;
        try {
            conn = DaoConnection.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setInt(1, id);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLDaoException(LOGGER_NAME, CLASS_NAME, " SQL close error : " + sql + " ; id - " + id, e);
        } finally {
            closePreparedStatement(pStatement, sql);
            closeConnection(conn, sql);
        }
    }

    protected int createEmptyRecordWithOneFieldAndGetNewId(String fieldName, Object value, TypeOfFiled valueType) {
        String sql = "INSERT INTO " + getTableName() + " ( " + fieldName + " ) VALUES (?);";

        int resultId = 0;
        Connection conn = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            conn = DaoConnection.getConnection();
            pStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            setDataToPrepareStatement(pStatement, 1, valueType, value);
            pStatement.executeUpdate();
            resultSet = pStatement.getGeneratedKeys();
            if (resultSet.next()) {
                resultId = resultSet.getInt(getIdName());
            }
        } catch (SQLException e) {
            throw new SQLDaoException(LOGGER_NAME, CLASS_NAME, " SQL close error : " + sql + " ; value - " + value, e);
        } finally {
            closeResultSet(resultSet, sql);
            closePreparedStatement(pStatement, sql);
            closeConnection(conn, sql);
        }
        return resultId;
    }

    protected void setValueToFieldById(String fieldName, Object value, TypeOfFiled valueType, int id) {
        StringBuilder tempSql = new StringBuilder().append("UPDATE ").append(getTableName()).append(" SET ");
        tempSql.append(fieldName).append(" = ?");
        tempSql.append(" WHERE ").append(getIdName()).append("= ?;");
        String sql = tempSql.toString();

        Connection conn = null;
        PreparedStatement pStatement = null;
        try {
            conn = DaoConnection.getConnection();
            pStatement = conn.prepareStatement(sql);
            setDataToPrepareStatement(pStatement, 1, valueType, value);
            setDataToPrepareStatement(pStatement, 2, getTypeOfField(1), id); // set ID
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLDaoException(LOGGER_NAME, CLASS_NAME, " SQL execute error : " + sql + " ;", e);
        } finally {
            closePreparedStatement(pStatement, sql);
            closeConnection(conn, sql);
        }
    }

    protected void deleteRecordById(int id) {
        String sql = "DELETE FROM " + getTableName() + " WHERE " + getIdName() + " = ?;";

        Connection conn = null;
        PreparedStatement pStatement = null;
        try {
            conn = DaoConnection.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setInt(1, id);
            pStatement.executeUpdate();

        } catch (SQLException e) {
            throw new SQLDaoException(LOGGER_NAME, CLASS_NAME, " SQL execute error : " + sql + " ; id - " + id, e);
        } finally {
            closePreparedStatement(pStatement, sql);
            closeConnection(conn, sql);
        }
    }

    protected TypeOfFiled getTypeOfField(int i) {
        // count field in table start from 1 instead 0
        if ((i > 0) || (i <= getQuantityOfFields())) {
            return getFieldTypeInTable(i - 1);
        }
        throw new WrongSetFieldDaoException(LOGGER_NAME, CLASS_NAME, "Incorrect set type of field. Quantity - " +
                getQuantityOfFields() + " ; search - " + i, new RuntimeException());
    }

    protected String getNameOfField(int i) {
        // count field in table start from 1 instead 0
        if ((i > 0) || (i <= getQuantityOfFields())) {
            return getFieldNameInTable(i - 1);
        }
        throw new WrongSetFieldDaoException(LOGGER_NAME, CLASS_NAME, "Incorrect set name of field. Quantity - " +
                getQuantityOfFields() + " ; search - " + i, new RuntimeException());
    }

    protected Object getDataFromResultSet(int i, ResultSet resultSet) throws SQLException {
        switch (getTypeOfField(i)) {
            case INT: {
                return (resultSet.getInt(i));
            }
            case LONG: {
                return (resultSet.getLong(i));
            }
            case DATATIME: {
                return (resultSet.getTimestamp(i));
            }
            case STRING: {
                return (resultSet.getString(i));
            }
            case BOOLEAN: {
                return (resultSet.getBoolean(i));
            }
            case DOUBLE: {
                return (resultSet.getDouble(i));
            }
            default: {
                throw new UnknownTypeOfFieldDaoException(LOGGER_NAME, CLASS_NAME, "Unknown type of field in table - "
                        + getTableName() + " ;" + " type - " + getTypeOfField(i), new RuntimeException());
            }
        }
    }

    protected void setDataToPrepareStatement(PreparedStatement pStatement, int placeInPStatement,
                                             TypeOfFiled typeOfFiled, Object data) throws SQLException {
        switch (typeOfFiled) {
            case INT: {
                pStatement.setInt(placeInPStatement, (Integer) data);
                return;
            }
            case LONG: {
                pStatement.setLong(placeInPStatement, (Long) data);
                return;
            }
            case DATATIME: {
                pStatement.setTimestamp(placeInPStatement, new Timestamp(((DateTime) data).getMillis()));
                return;
            }
            case STRING: {
                pStatement.setString(placeInPStatement, (String) data);
                return;
            }
            case BOOLEAN: {
                pStatement.setBoolean(placeInPStatement, (Boolean) data);
                return;
            }
            case DOUBLE: {
                pStatement.setDouble(placeInPStatement, (Double) data);
                return;
            }
            default: {
                throw new UnknownTypeOfFieldDaoException(LOGGER_NAME, CLASS_NAME, "Unknown type of field in table - "
                        + getTableName() + " ;" + " type - " + typeOfFiled, new RuntimeException());
            }
        }
    }
}
