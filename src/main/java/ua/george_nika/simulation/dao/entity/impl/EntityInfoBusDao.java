package ua.george_nika.simulation.dao.entity.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.entity.abstr.AbstractEntityInfoExtraDao;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.model.entity.impl.BusEntityInfo;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 04.12.2015.
 */
@Repository
public class EntityInfoBusDao extends AbstractEntityInfoExtraDao {

    private static final String TABLE_NAME = "entity_info_bus";
    private static final String ID_NAME = "id_entity_info";
    private static final int QUANTITY_OF_FIELDS = 3;


    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = EntityInfoBusDao.class.getSimpleName();

    static {
        DaoFactory.registerEntityInfoExtraClassInFactory(BusEntityInfo.ENTITY_INFO_TYPE,
                EntityInfoBusDao.class.getCanonicalName());
    }

    public EntityInfoBusDao() {
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("capacity");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("price_in_cent");
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
    protected void fillExtraDataInEntityInfo(List<Object> dataList, EntityInfo entityInfo) {
        if (dataList.size() != getQuantityOfFields()) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Wrong data. Data size - " + dataList.size() + " ; " +
                    "Quantity - " + getQuantityOfFields());
            throw new WrongDataDaoException("Data size - " + dataList.size() + " ; " +
                    "Quantity - " + getQuantityOfFields());
        }

        BusEntityInfo busEntityInfo = ClassTypeUtil.getCheckedClass(entityInfo, BusEntityInfo.class);
        busEntityInfo.setIdEntityInfo((Integer) dataList.get(0));
        busEntityInfo.setCapacity((Integer) dataList.get(1));
        busEntityInfo.setPriceInCent((Integer) dataList.get(2));
    }

    protected List<Object> getExtraDataFromEntityInfo(EntityInfo entityInfo) {
        List<Object> resultList = new ArrayList<>();
        BusEntityInfo busEntityInfo = ClassTypeUtil.getCheckedClass(entityInfo, BusEntityInfo.class);
        resultList.add(busEntityInfo.getIdEntityInfo());
        resultList.add(busEntityInfo.getCapacity());
        resultList.add(busEntityInfo.getPriceInCent());

        return resultList;
    }
}
