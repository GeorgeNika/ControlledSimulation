/**
 * work`s with "station" generator history
 */

package ua.george_nika.simulation.dao.generator.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.dao.generator.abstr.AbstractGeneratorHistoryExtraDao;
import ua.george_nika.simulation.model.generator.GeneratorHistory;
import ua.george_nika.simulation.model.generator.impl.StationGenerator;
import ua.george_nika.simulation.model.generator.impl.StationGeneratorHistory;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused","FieldCanBeLocal"})

@Repository
public class StationGeneratorHistoryExtraDao extends AbstractGeneratorHistoryExtraDao {

    private static final String TABLE_NAME = "gen_station_history";
    private static final String ID_NAME = "id_generator_history";
    private static final int QUANTITY_OF_FIELDS = 3;

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = RouteGeneratorHistoryExtraDao.class.getSimpleName();

    static {
        DaoFactory.registerGeneratorHistoryExtraClassInFactory(StationGenerator.GENERATOR_TYPE,
                StationGeneratorHistoryExtraDao.class.getCanonicalName());
    }

    public StationGeneratorHistoryExtraDao() {
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("send_count");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("receive_count");
    }

    protected String getTableName() {
        return TABLE_NAME;
    }

    protected String getIdName() {
        return ID_NAME;
    }

    protected int getQuantityOfFields() {
        return QUANTITY_OF_FIELDS;
    }

    @Override
    protected void fillExtraDataInGeneratorHistory(List<Object> dataList, GeneratorHistory generatorHistory) {
        if (dataList.size() != getQuantityOfFields()) {
            throw new WrongDataDaoException(LOGGER_NAME, CLASS_NAME, "Wrong data. Data size - " + dataList.size()
                    + " ; " + "Quantity - " + getQuantityOfFields(), new RuntimeException());
        }

        StationGeneratorHistory stationGeneratorHistory =
                ClassTypeUtil.getCheckedClass(generatorHistory, StationGeneratorHistory.class);

        stationGeneratorHistory.setIdGeneratorHistory((Integer) dataList.get(0));
        stationGeneratorHistory.setSendEntity((Integer) dataList.get(1));
        stationGeneratorHistory.setReceiveEntity((Integer) dataList.get(2));
    }

    @Override
    protected List<Object> getExtraDataFromGeneratorHistory(GeneratorHistory generatorHistory) {
        List<Object> resultList = new ArrayList<>();
        StationGeneratorHistory stationGeneratorHistory =
                ClassTypeUtil.getCheckedClass(generatorHistory, StationGeneratorHistory.class);

        resultList.add(stationGeneratorHistory.getIdGeneratorHistory());
        resultList.add(stationGeneratorHistory.getSendEntity());
        resultList.add(stationGeneratorHistory.getReceiveEntity());

        return resultList;
    }
}
