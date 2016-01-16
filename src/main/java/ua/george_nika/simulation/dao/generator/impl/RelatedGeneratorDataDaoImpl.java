package ua.george_nika.simulation.dao.generator.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.generator.RelatedGeneratorDataDao;
import ua.george_nika.simulation.dao.error.SQLDaoException;
import ua.george_nika.simulation.model.generator.RelatedGeneratorData;
import ua.george_nika.simulation.util.AppLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 20.12.2015.
 */
@Repository
public class RelatedGeneratorDataDaoImpl implements RelatedGeneratorDataDao{

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = RelatedGeneratorDataDaoImpl.class.getSimpleName();

    public List<RelatedGeneratorData> getAllRelatedGeneratorDataById(int idGenerator) {
        String sql = "SELECT G.generator_name, G.generator_type, " +
                "GG.id_related_generator, GG.delay_ms, GG.related_position " +
                "FROM  generator G, gen_gen_manytomany GG " +
                "WHERE GG.id_generator = ? " +
                "AND G.id_generator = GG.id_related_generator " +
                "ORDER BY GG.related_position ;";

        List<RelatedGeneratorData> resultRGInfoList = new ArrayList<RelatedGeneratorData>();

        Connection conn = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            conn = DaoFactory.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setInt(1, idGenerator);
            resultSet = pStatement.executeQuery();
            RelatedGeneratorData tempRGInfo;
            while (resultSet.next()) {
                tempRGInfo = new RelatedGeneratorData();

                tempRGInfo.setIdRelatedGenerator(resultSet.getInt("id_related_generator"));
                tempRGInfo.setRelatedGeneratorType(resultSet.getString("generator_type"));
                tempRGInfo.setRelatedGeneratorName(resultSet.getString("generator_name"));
                tempRGInfo.setDelayMs(resultSet.getLong("delay_ms"));
                tempRGInfo.setRelatedPosition(resultSet.getInt("related_position"));

                resultRGInfoList.add(tempRGInfo);
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
        return resultRGInfoList;
    }

    public void setDelayToRelatedGeneratorData(int idGenerator, int relatedPosition, long delay) {
        String sql = "UPDATE gen_gen_manytomany  SET delay_ms = ? " +
                "WHERE id_generator = ? AND related_position = ?";

        Connection conn = null;
        PreparedStatement pStatement = null;
        try {
            conn = DaoFactory.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setLong(1, delay);
            pStatement.setInt(2, idGenerator);
            pStatement.setInt(3, relatedPosition);

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

    public void upPositionToRelatedGeneratorData(int idGenerator, int position) {
        String sql = "UPDATE gen_gen_manytomany SET related_position =" +
                "CASE " +
                "WHEN related_position = ? AND ? = 1 then " +
                "(SELECT max(related_position) FROM gen_gen_manytomany WHERE id_generator = ? ) " +
                "WHEN related_position <> ? AND ? = 1 then related_position - 1 " +
                "WHEN related_position = ? AND ? <> 1 then related_position - 1 " +
                "WHEN (related_position + 1) = ? AND ? <>1 then related_position + 1 " +
                "ELSE related_position " +
                "END " +
                "WHERE id_generator = ?; ";

        Connection conn = null;
        PreparedStatement pStatement = null;
        try {
            conn = DaoFactory.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setInt(1, position);
            pStatement.setInt(2, position);
            pStatement.setInt(3, idGenerator);

            pStatement.setInt(4, position);
            pStatement.setInt(5, position);

            pStatement.setInt(6, position);
            pStatement.setInt(7, position);

            pStatement.setInt(8, position);
            pStatement.setInt(9, position);

            pStatement.setInt(10, idGenerator);

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

    public void downPositionToRelatedGeneratorData(int idGenerator, int position) {
        String sqlMaxPosition = "(SELECT max(related_position) FROM gen_gen_manytomany WHERE id_generator = ?)";
        String sql = "UPDATE gen_gen_manytomany SET related_position =" +
                "CASE " +
                "WHEN related_position = " + sqlMaxPosition + " AND ? = " + sqlMaxPosition + " then 1" +
                "WHEN related_position <> " + sqlMaxPosition + " AND ? = " + sqlMaxPosition + " then related_position + 1 " +
                "WHEN related_position = ? AND ? <> " + sqlMaxPosition + " then related_position + 1 " +
                "WHEN (related_position - 1) = ? AND ? <>" + sqlMaxPosition + " then related_position - 1 " +
                "ELSE related_position " +
                "END " +
                "WHERE id_generator = ?; ";

        Connection conn = null;
        PreparedStatement pStatement = null;
        try {
            conn = DaoFactory.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setInt(1, idGenerator);
            pStatement.setInt(2, position);
            pStatement.setInt(3, idGenerator);

            pStatement.setInt(4, idGenerator);
            pStatement.setInt(5, position);
            pStatement.setInt(6, idGenerator);

            pStatement.setInt(7, position);
            pStatement.setInt(8, position);
            pStatement.setInt(9, idGenerator);

            pStatement.setInt(10, position);
            pStatement.setInt(11, position);
            pStatement.setInt(12, idGenerator);

            pStatement.setInt(13, idGenerator);

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

    public void createRelatedGeneratorData(int idGenerator, int idRelatedGenerator) {
        String sql = "INSERT INTO gen_gen_manytomany  (id_generator, id_related_generator, related_position) " +
                "SELECT ?,?, COALESCE(max(related_position)+1,1) from gen_gen_manytomany WHERE id_generator=?;";
        Connection conn = null;
        PreparedStatement pStatement = null;
        try {
            conn = DaoFactory.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setInt(1, idGenerator);
            pStatement.setInt(2, idRelatedGenerator);
            pStatement.setInt(3, idGenerator);

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

    public void updateRelatedGeneratorData(int idGenerator, int idRelatedGenerator, int position) {
        String sql = "UPDATE gen_gen_manytomany SET id_related_generator = ?" +
                "WHERE id_generator = ? AND related_position = ?; ";

        Connection conn = null;
        PreparedStatement pStatement = null;
        try {
            conn = DaoFactory.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setInt(1, idRelatedGenerator);
            pStatement.setInt(2, idGenerator);
            pStatement.setInt(3, position);

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

    public void deleteRelatedGeneratorData(int idGenerator, int position) {
        String sql = "DELETE FROM gen_gen_manytomany WHERE id_generator = ? AND related_position = ?; ";

        String sqlNext = "UPDATE gen_gen_manytomany SET related_position =" +
                "CASE " +
                "WHEN related_position > ? then related_position - 1 " +
                "ELSE related_position " +
                "END " +
                "WHERE id_generator = ?; ";

        Connection conn = null;
        PreparedStatement pStatement = null;
        try {
            conn = DaoFactory.getConnection();
            conn.setAutoCommit(false);

            pStatement = conn.prepareStatement(sql);
            pStatement.setInt(1, idGenerator);
            pStatement.setInt(2, position);
            pStatement.executeUpdate();

            if (pStatement != null) {
                pStatement.close();
            }

            sql = sqlNext;
            pStatement = conn.prepareStatement(sql);
            pStatement.setInt(1, position);
            pStatement.setInt(2, idGenerator);
            pStatement.executeUpdate();


            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    AppLog.error(LOGGER_NAME, CLASS_NAME, " ROLLBACK execute error : " + sql + " ;", e);
                    throw new SQLDaoException(" ROLLBACK execute error : " + sql + " ;", e);
                }
            }

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
