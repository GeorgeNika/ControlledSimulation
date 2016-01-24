package ua.george_nika.simulation.dao.entity.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.entity.abstr.AbstractEntityInfoExtraDao;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.model.entity.impl.EconomyHumanEntity;
import ua.george_nika.simulation.model.entity.impl.EconomyHumanEntityInfo;
import ua.george_nika.simulation.model.entity.impl.HumanEntityInfo;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 04.12.2015.
 */
@Repository
public class EntityInfoEconomyHumanDao extends AbstractEntityInfoExtraDao {

    private static final String TABLE_NAME = "entity_info_economy_human";
    private static final String ID_NAME = "id_entity_info";
    private static final int QUANTITY_OF_FIELDS = 4;


    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = EntityInfoEconomyHumanDao.class.getSimpleName();

    static {
        DaoFactory.registerEntityInfoExtraClassInFactory(EconomyHumanEntityInfo.ENTITY_INFO_TYPE,
                EntityInfoEconomyHumanDao.class.getCanonicalName());
    }

    public EntityInfoEconomyHumanDao() {
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("reasonable_price_in_cent");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("max_price_in_cent");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("wait_time_ms");
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

        EconomyHumanEntityInfo economyHumanEntityInfo
                = ClassTypeUtil.getCheckedClass(entityInfo, EconomyHumanEntityInfo.class);
        economyHumanEntityInfo.setIdEntityInfo((Integer) dataList.get(0));
        economyHumanEntityInfo.setReasonablePriceInCent((Integer) dataList.get(1));
        economyHumanEntityInfo.setMaxPriceInCent((Integer) dataList.get(2));
        economyHumanEntityInfo.setDelayTimeToRemoveMs((Integer) dataList.get(3));
    }

    protected List<Object> getExtraDataFromEntityInfo(EntityInfo entityInfo) {
        List<Object> resultList = new ArrayList<>();

        EconomyHumanEntityInfo economyHumanEntityInfo
                = ClassTypeUtil.getCheckedClass(entityInfo, EconomyHumanEntityInfo.class);
        resultList.add(economyHumanEntityInfo.getIdEntityInfo());
        resultList.add(economyHumanEntityInfo.getReasonablePriceInCent());
        resultList.add(economyHumanEntityInfo.getMaxPriceInCent());
        resultList.add(economyHumanEntityInfo.getDelayTimeToRemoveMs());

        return resultList;
    }
}
