package ua.george_nika.simulation.dao.entity.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.entity.abstr.AbstractEntityHistoryExtraDao;
import ua.george_nika.simulation.dao.entity.abstr.AbstractEntityInfoExtraDao;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.model.entity.EntityHistory;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.model.entity.impl.BusEntity;
import ua.george_nika.simulation.model.entity.impl.BusEntityInfo;
import ua.george_nika.simulation.model.entity.impl.BusHistory;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 04.12.2015.
 */
@Repository
public class EntityBusHistoryDao extends AbstractEntityHistoryExtraDao {

    private static final String TABLE_NAME = "ent_bus_history";
    private static final String ID_NAME = "id_entity_history";
    private static final int QUANTITY_OF_FIELDS = 3;

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = EntityBusHistoryDao.class.getSimpleName();

    static {
        DaoFactory.registerEntityHistoryExtraClassInFactory(BusEntity.ENTITY_TYPE,
                EntityBusHistoryDao.class.getCanonicalName());
    }

    public EntityBusHistoryDao() {
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
    protected void fillExtraDataInEntityHistory(List<Object> dataList, EntityHistory entityHistory) {
        if (dataList.size() != getQuantityOfFields()) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Wrong data. Data size - " + dataList.size() + " ; " +
                    "Quantity - " + getQuantityOfFields());
            throw new WrongDataDaoException("Data size - " + dataList.size() + " ; " +
                    "Quantity - " + getQuantityOfFields());
        }

        BusHistory busHistory = ClassTypeUtil.getCheckedClass(entityHistory, BusHistory.class);
        busHistory.setIdEntityHistory((Integer) dataList.get(0));
        busHistory.setProcessedEntity((Integer) dataList.get(1));
        busHistory.setTotalAmount((Long) dataList.get(2));
    }

    @Override
    protected List<Object> getExtraDataFromEntityHistory(EntityHistory entityHistory) {
        List<Object> resultList = new ArrayList<>();
        BusHistory busHistory = ClassTypeUtil.getCheckedClass(entityHistory, BusHistory.class);

        resultList.add(busHistory.getIdEntityHistory());
        resultList.add(busHistory.getProcessedEntity());
        resultList.add(busHistory.getTotalAmount());


        return resultList;
    }
}

