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

/**
 * Created by george on 12.01.2016.
 */
@Repository
public class StationGeneratorHistoryExtraDao extends AbstractGeneratorHistoryExtraDao {

    protected static final String TABLE_NAME = "gen_station_history";
    protected static final String ID_NAME = "id_generator_history";
    protected static final int QUANTITY_OF_FIELDS = 5;


    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = RouteGeneratorHistoryExtraDao.class.getSimpleName();

    static {
        DaoFactory.registerGeneratorHistoryExtraClassInFactory(StationGenerator.GENERATOR_TYPE,
                StationGeneratorHistoryExtraDao.class.getCanonicalName());
    }

    public StationGeneratorHistoryExtraDao(){
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("create_count");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("send_count");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("receive_count");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("destroy_count");
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
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Wrong data. Data size - " + dataList.size() + " ; " +
                    "Quantity - " + getQuantityOfFields());
            throw new WrongDataDaoException("Data size - " + dataList.size() + " ; " +
                    "Quantity - " + getQuantityOfFields());
        }

        StationGeneratorHistory stationGeneratorHistory =
                ClassTypeUtil.getCheckedClass(generatorHistory, StationGeneratorHistory.class);

        stationGeneratorHistory.setIdGeneratorHistory((Integer) dataList.get(0));
        stationGeneratorHistory.setCreateEntity((Integer) dataList.get(1));
        stationGeneratorHistory.setSendEntity((Integer) dataList.get(2));
        stationGeneratorHistory.setReceiveEntity((Integer) dataList.get(3));
        stationGeneratorHistory.setDestroyEntity((Integer) dataList.get(4));
    }

    @Override
    protected List<Object> getExtraDataFromGeneratorHistory(GeneratorHistory generatorHistory) {
        List<Object> resultList = new ArrayList<>();
        StationGeneratorHistory stationGeneratorHistory =
                ClassTypeUtil.getCheckedClass(generatorHistory, StationGeneratorHistory.class);

        resultList.add(stationGeneratorHistory.getIdGeneratorHistory());
        resultList.add(stationGeneratorHistory.getCreateEntity());
        resultList.add(stationGeneratorHistory.getSendEntity());
        resultList.add(stationGeneratorHistory.getReceiveEntity());
        resultList.add(stationGeneratorHistory.getDestroyEntity());

        return resultList;
    }
}
