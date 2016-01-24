package ua.george_nika.simulation.dao.generator.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.dao.generator.GeneratorHistoryDao;
import ua.george_nika.simulation.dao.generator.abstr.AbstractGeneratorHistoryDao;
import ua.george_nika.simulation.model.generator.GeneratorHistory;
import ua.george_nika.simulation.model.generator.GeneratorHistoryFactory;
import ua.george_nika.simulation.util.AppLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 11.01.2016.
 */
@Repository
public class GeneratorHistoryDaoImpl extends AbstractGeneratorHistoryDao {

    private static final String TABLE_NAME = "generator_history";
    private static final String ID_NAME = "id_generator_history";
    private static final int QUANTITY_OF_FIELDS = 8;

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = GeneratorDaoImpl.class.getSimpleName();

    public GeneratorHistoryDaoImpl() {
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("id_generator");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("id_experiment_history");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("id_experiment");

        fieldTypeInTable.add(TypeOfFiled.STRING);
        fieldNameInTable.add("generator_name");

        fieldTypeInTable.add(TypeOfFiled.STRING);
        fieldNameInTable.add("generator_type");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("create_entity_count");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("destroy_entity_count");
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
    protected GeneratorHistory getGeneratorHistoryFromData(List<Object> dataList) {
        String type = (String) dataList.get(5);
        GeneratorHistory result = GeneratorHistoryFactory.getGeneratorHistoryByType(type);
        if (dataList.size() != getQuantityOfFields()) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Wrong data. Data size - " + dataList.size() + " ; " +
                    "Quantity - " + getQuantityOfFields());
            throw new WrongDataDaoException("Data size - " + dataList.size() + " ; " +
                    "Quantity - " + getQuantityOfFields());
        }

        result.setIdGeneratorHistory((Integer) dataList.get(0));
        result.setIdGenerator((Integer) dataList.get(1));
        result.setIdExperimentHistory((Integer) dataList.get(2));
        result.setIdExperiment((Integer) dataList.get(3));
        result.setGeneratorName(dataList.get(4) == null ? "" : (String) dataList.get(4));
        result.setGeneratorType(dataList.get(5) == null ? "" : (String) dataList.get(5));
        result.setCreateEntity((Integer) dataList.get(6));
        result.setDestroyEntity((Integer) dataList.get(7));

        return result;
    }

    @Override
    protected List<Object> getDataFromGeneratorHistory(GeneratorHistory generatorHistory) {
        List<Object> resultList = new ArrayList<Object>();

        resultList.add(generatorHistory.getIdGeneratorHistory());
        resultList.add(generatorHistory.getIdGenerator());
        resultList.add(generatorHistory.getIdExperimentHistory());
        resultList.add(generatorHistory.getIdExperiment());
        resultList.add(generatorHistory.getGeneratorName());
        resultList.add(generatorHistory.getGeneratorType());
        resultList.add(generatorHistory.getCreateEntity());
        resultList.add(generatorHistory.getDestroyEntity());

        return resultList;
    }

}
