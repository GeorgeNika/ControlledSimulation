package ua.george_nika.simulation.dao.entity.impl;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.entity.abstr.AbstractEntityHistoryDao;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.dao.experiment.abstr.AbstractExperimentHistoryDao;
import ua.george_nika.simulation.dao.experiment.impl.ExperimentDaoImpl;
import ua.george_nika.simulation.model.entity.EntityHistory;
import ua.george_nika.simulation.model.entity.EntityHistoryFactory;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.model.experiment.ExperimentHistoryFactory;
import ua.george_nika.simulation.util.AppLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 05.01.2016.
 */
@Repository
public class EntityHistoryDaoImpl extends AbstractEntityHistoryDao {

    protected static final String TABLE_NAME = "entity_history";
    protected static final String ID_NAME = "id_entity_history";
    protected static final int QUANTITY_OF_FIELDS = 8;


    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = EntityHistoryDaoImpl.class.getSimpleName();

    public EntityHistoryDaoImpl() {
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("id_entity");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("id_generator_history");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("id_generator");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("id_experiment_history");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("id_experiment");

        fieldTypeInTable.add(TypeOfFiled.STRING);
        fieldNameInTable.add("entity_type");

        fieldTypeInTable.add(TypeOfFiled.DATATIME);
        fieldNameInTable.add("create_time");
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
    protected EntityHistory getEntityHistoryFromData(List<Object> dataList) {
        String type = (String) dataList.get(6);
        EntityHistory result = EntityHistoryFactory.getEntityHistoryByType(type);
        if (dataList.size() != getQuantityOfFields()) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Wrong data. Data size - " + dataList.size() + " ; " +
                    "Quantity - " + getQuantityOfFields());
            throw new WrongDataDaoException("Data size - " + dataList.size() + " ; " +
                    "Quantity - " + getQuantityOfFields());
        }

        result.setIdEntityHistory((Integer) dataList.get(0));
        result.setIdEntity((Integer) dataList.get(1));
        result.setIdGeneratorHistory((Integer) dataList.get(2));
        result.setIdGenerator((Integer) dataList.get(3));
        result.setIdExperimentHistory((Integer) dataList.get(4));
        result.setIdExperiment((Integer) dataList.get(5));
        result.setEntityType((String) dataList.get(6));
        result.setCreateTime(dataList.get(7) == null ? new DateTime(0) : new DateTime(dataList.get(7)));

        return result;
    }

    @Override
    protected List<Object> getDataFromEntityHistory(EntityHistory entityHistory) {
        List<Object> resultList = new ArrayList<Object>();

        resultList.add(entityHistory.getIdEntityHistory());
        resultList.add(entityHistory.getIdEntity());
        resultList.add(entityHistory.getIdGeneratorHistory());
        resultList.add(entityHistory.getIdGenerator());
        resultList.add(entityHistory.getIdExperimentHistory());
        resultList.add(entityHistory.getIdExperiment());
        resultList.add(entityHistory.getEntityType());
        resultList.add(entityHistory.getCreateTime());

        return resultList;
    }


}
