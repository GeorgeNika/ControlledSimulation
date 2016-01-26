/**
 * work`s with "route" generator history
 */

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

@SuppressWarnings({"unused","FieldCanBeLocal"})

@Repository
public class RouteGeneratorHistoryExtraDao extends AbstractGeneratorHistoryExtraDao {

    private static final String TABLE_NAME = "gen_route_history";
    private static final String ID_NAME = "id_generator_history";
    private static final int QUANTITY_OF_FIELDS = 3;

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
        fieldNameInTable.add("processed_entity");

        fieldTypeInTable.add(TypeOfFiled.LONG);
        fieldNameInTable.add("total_amount");
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
                    + " ; " +                    "Quantity - " + getQuantityOfFields(), new RuntimeException());
        }

        RouteGeneratorHistory routeGeneratorHistory =
                ClassTypeUtil.getCheckedClass(generatorHistory, RouteGeneratorHistory.class);

        routeGeneratorHistory.setIdGeneratorHistory((Integer) dataList.get(0));
        routeGeneratorHistory.setProcessedEntity((Integer) dataList.get(1));
        routeGeneratorHistory.setTotalAmount((Long) dataList.get(2));
    }

    @Override
    protected List<Object> getExtraDataFromGeneratorHistory(GeneratorHistory generatorHistory) {
        List<Object> resultList = new ArrayList<>();
        RouteGeneratorHistory routeGeneratorHistory =
                ClassTypeUtil.getCheckedClass(generatorHistory, RouteGeneratorHistory.class);

        resultList.add(routeGeneratorHistory.getIdGeneratorHistory());
        resultList.add(routeGeneratorHistory.getProcessedEntity());
        resultList.add(routeGeneratorHistory.getTotalAmount());

        return resultList;
    }
}
