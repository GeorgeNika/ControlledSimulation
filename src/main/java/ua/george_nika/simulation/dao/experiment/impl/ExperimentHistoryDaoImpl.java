/**
 * work`s with experiment history common table
 */

package ua.george_nika.simulation.dao.experiment.impl;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.dao.experiment.abstr.AbstractExperimentHistoryDao;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.model.experiment.ExperimentHistoryFactory;
import ua.george_nika.simulation.util.AppLog;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

@Repository
public class ExperimentHistoryDaoImpl extends AbstractExperimentHistoryDao {

    protected static final String TABLE_NAME = "experiment_history";
    protected static final String ID_NAME = "id_experiment_history";
    protected static final int QUANTITY_OF_FIELDS = 11;


    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = ExperimentHistoryDaoImpl.class.getSimpleName();

    public ExperimentHistoryDaoImpl() {
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("id_experiment");

        fieldTypeInTable.add(TypeOfFiled.STRING);
        fieldNameInTable.add("experiment_name");

        fieldTypeInTable.add(TypeOfFiled.STRING);
        fieldNameInTable.add("experiment_type");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("generator_quantity");

        fieldTypeInTable.add(TypeOfFiled.DATATIME);
        fieldNameInTable.add("execute_date");

        fieldTypeInTable.add(TypeOfFiled.DATATIME);
        fieldNameInTable.add("experiment_start");

        fieldTypeInTable.add(TypeOfFiled.DATATIME);
        fieldNameInTable.add("experiment_end");

        fieldTypeInTable.add(TypeOfFiled.DATATIME);
        fieldNameInTable.add("last_point");

        fieldTypeInTable.add(TypeOfFiled.STRING);
        fieldNameInTable.add("log_file");

        fieldTypeInTable.add(TypeOfFiled.STRING);
        fieldNameInTable.add("xml_file");
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
    protected ExperimentHistory getExperimentHistoryFromData(List<Object> dataList) {
        String type = (String) dataList.get(3);
        ExperimentHistory result = ExperimentHistoryFactory.getExperimentHistoryByType(type);
        if (dataList.size() != getQuantityOfFields()) {
            throw new WrongDataDaoException(LOGGER_NAME, CLASS_NAME, "Wrong data. Data size - " + dataList.size()
                    + " ; " + "Quantity - " + getQuantityOfFields(), new RuntimeException());
        }

        result.setIdExperimentHistory((Integer) dataList.get(0));
        result.setIdExperiment((Integer) dataList.get(1));
        result.setExperimentName(dataList.get(2) == null ? "" : (String) dataList.get(2));
        result.setExperimentType((String) dataList.get(3));
        result.setGeneratorQuantity((Integer) dataList.get(4));
        result.setExecuteDate(dataList.get(5) == null ? new DateTime(0) : new DateTime(dataList.get(5)));
        result.setExperimentStart(dataList.get(6) == null ? new DateTime(0) : new DateTime(dataList.get(6)));
        result.setExperimentEnd(dataList.get(7) == null ? new DateTime(0) : new DateTime(dataList.get(7)));
        result.setLastPoint(dataList.get(8) == null ? new MutableDateTime(0) : new MutableDateTime(dataList.get(8)));
        result.setLogFile(dataList.get(9) == null ? "" : (String) dataList.get(9));
        result.setXmlFile(dataList.get(10) == null ? "" : (String) dataList.get(10));

        return result;
    }

    @Override
    protected List<Object> getDataFromExperimentHistory(ExperimentHistory experimentHistory) {
        List<Object> resultList = new ArrayList<>();

        resultList.add(experimentHistory.getIdExperimentHistory());
        resultList.add(experimentHistory.getIdExperiment());
        resultList.add(experimentHistory.getExperimentName());
        resultList.add(experimentHistory.getExperimentType());
        resultList.add(experimentHistory.getGeneratorQuantity());
        resultList.add(experimentHistory.getExecuteDate());
        resultList.add(experimentHistory.getExperimentStart());
        resultList.add(experimentHistory.getExperimentEnd());
        resultList.add(experimentHistory.getLastPoint().toDateTime());
        resultList.add(experimentHistory.getLogFile());
        resultList.add(experimentHistory.getXmlFile());

        return resultList;
    }


}
