package ua.george_nika.simulation.dao.generator.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.dao.generator.abstr.AbstractGeneratorHistoryExtraDao;
import ua.george_nika.simulation.model.generator.GeneratorHistory;
import ua.george_nika.simulation.model.generator.impl.RouteGenerator;
import ua.george_nika.simulation.model.generator.impl.RouteGeneratorHistory;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 12.01.2016.
 */
@Repository
public class RouteGeneratorHistoryExtraDao extends AbstractGeneratorHistoryExtraDao {
    //todo
    protected static final String TABLE_NAME = "gen_route_history";
    protected static final String ID_NAME = "id_generator_history";
    protected static final int QUANTITY_OF_FIELDS = 2;

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = RouteGeneratorHistoryExtraDao.class.getSimpleName();

    static {
        DaoFactory.registerGeneratorHistoryExtraClassInFactory(RouteGenerator.GENERATOR_TYPE,
                RouteGeneratorHistoryExtraDao.class.getCanonicalName());
    }

    public RouteGeneratorHistoryExtraDao(){
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("entity_count");

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

        RouteGeneratorHistory routeGeneratorHistory =
                ClassTypeUtil.getCheckedClass(generatorHistory, RouteGeneratorHistory.class);

        routeGeneratorHistory.setIdGeneratorHistory((Integer) dataList.get(0));
        // todo routeGeneratorHistory.setIdGeneratorHistory((Integer) dataList.get(1));
    }

    @Override
    protected List<Object> getExtraDataFromGeneratorHistory(GeneratorHistory generatorHistory) {
        List<Object> resultList = new ArrayList<>();
        RouteGeneratorHistory routeGeneratorHistory =
                ClassTypeUtil.getCheckedClass(generatorHistory, RouteGeneratorHistory.class);

        resultList.add(routeGeneratorHistory.getIdGeneratorHistory());
        resultList.add(22);

        return resultList;
    }
}
