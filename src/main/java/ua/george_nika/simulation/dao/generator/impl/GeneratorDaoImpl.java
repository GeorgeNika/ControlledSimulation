/**
 * work`s with generator common table
 */

package ua.george_nika.simulation.dao.generator.impl;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.generator.abstr.AbstractGeneratorDao;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.GeneratorFactory;
import ua.george_nika.simulation.util.AppLog;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused","FieldCanBeLocal"})

@Repository
public class GeneratorDaoImpl extends AbstractGeneratorDao {

    private static final String TABLE_NAME = "generator";
    private static final String ID_NAME = "id_generator";
    private static final int QUANTITY_OF_FIELDS = 8;

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = GeneratorDaoImpl.class.getSimpleName();

    public GeneratorDaoImpl() {
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.STRING);
        fieldNameInTable.add("generator_name");

        fieldTypeInTable.add(TypeOfFiled.STRING);
        fieldNameInTable.add("generator_type");

        fieldTypeInTable.add(TypeOfFiled.STRING);
        fieldNameInTable.add("entity_type");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("id_entity");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("entity_quantity");

        fieldTypeInTable.add(TypeOfFiled.DATATIME);
        fieldNameInTable.add("created");

        fieldTypeInTable.add(TypeOfFiled.DATATIME);
        fieldNameInTable.add("updated");
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

    protected Generator getGeneratorFromData(List<Object> dataList) {
        String type = (String) dataList.get(2);
        Generator result = GeneratorFactory.getGeneratorByType(type);
        if (dataList.size() != getQuantityOfFields()) {
            throw new WrongDataDaoException(LOGGER_NAME, CLASS_NAME, "Wrong data. Data size - " + dataList.size()
                    + " ; " + "Quantity - " + getQuantityOfFields(), new RuntimeException());
        }

        result.setIdGenerator((Integer) dataList.get(0));
        result.setGeneratorName(dataList.get(1) == null ? "" : (String) dataList.get(1));
        // type skip dataList.get(2)
        result.setEntityType(dataList.get(3) == null ? "" : (String) dataList.get(3));
        result.setIdEntityInfo((Integer) dataList.get(4));
        result.setEntityQuantity((Integer) dataList.get(5));
        result.setCreated(dataList.get(6) == null ? new DateTime(0) : new DateTime(dataList.get(6)));
        result.setUpdated(dataList.get(7) == null ? new DateTime(0) : new DateTime(dataList.get(7)));

        return result;
    }

    protected List<Object> getDataFromGenerator(Generator generator) {
        List<Object> resultList = new ArrayList<>();

        resultList.add(generator.getIdGenerator());
        resultList.add(generator.getGeneratorName());
        resultList.add(generator.getGeneratorType());
        resultList.add(generator.getEntityType());
        resultList.add(generator.getIdEntityInfo());
        resultList.add(generator.getEntityQuantity());
        resultList.add(generator.getCreated());
        resultList.add(generator.getUpdated());

        return resultList;
    }
}
