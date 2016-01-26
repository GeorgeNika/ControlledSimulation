/**
 * Work with "human" entity info table
 */

package ua.george_nika.simulation.dao.entity.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.entity.abstr.AbstractEntityInfoExtraDao;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.model.entity.impl.HumanEntityInfo;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused","FieldCanBeLocal"})

@Repository
public class EntityInfoHumanDao extends AbstractEntityInfoExtraDao {

    private static final String TABLE_NAME = "entity_info_human";
    private static final String ID_NAME = "id_entity_info";
    private static final int QUANTITY_OF_FIELDS = 3;


    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = EntityInfoHumanDao.class.getSimpleName();

    static {
        DaoFactory.registerEntityInfoExtraClassInFactory(HumanEntityInfo.ENTITY_INFO_TYPE,
                EntityInfoHumanDao.class.getCanonicalName());
    }

    public EntityInfoHumanDao() {
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("reasonable_price_in_cent");

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
            throw new WrongDataDaoException(LOGGER_NAME, CLASS_NAME, "Wrong data. Data size - " + dataList.size()
                    + " ; " + "Quantity - " + getQuantityOfFields(), new RuntimeException());
        }

        HumanEntityInfo humanEntityInfo = ClassTypeUtil.getCheckedClass(entityInfo, HumanEntityInfo.class);
        humanEntityInfo.setIdEntityInfo((Integer) dataList.get(0));
        humanEntityInfo.setReasonablePriceInCent((Integer) dataList.get(1));
        humanEntityInfo.setDelayTimeToRemoveMs((Integer) dataList.get(2));
    }

    protected List<Object> getExtraDataFromEntityInfo(EntityInfo entityInfo) {
        List<Object> resultList = new ArrayList<>();

        HumanEntityInfo humanEntityInfo = ClassTypeUtil.getCheckedClass(entityInfo, HumanEntityInfo.class);
        resultList.add(humanEntityInfo.getIdEntityInfo());
        resultList.add(humanEntityInfo.getReasonablePriceInCent());
        resultList.add(humanEntityInfo.getDelayTimeToRemoveMs());

        return resultList;
    }
}
