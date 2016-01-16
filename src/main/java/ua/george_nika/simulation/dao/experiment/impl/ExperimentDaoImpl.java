package ua.george_nika.simulation.dao.experiment.impl;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.experiment.abstr.AbstractExperimentDao;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentFactory;
import ua.george_nika.simulation.util.AppLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 06.12.2015.
 */
@Repository
public class ExperimentDaoImpl extends AbstractExperimentDao {

    private static final String TABLE_NAME = "experiment";
    private static final String ID_NAME = "id_experiment";
    private static final int QUANTITY_OF_FIELDS = 8;

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = ExperimentDaoImpl.class.getSimpleName();

    public ExperimentDaoImpl() {
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.STRING);
        fieldNameInTable.add("experiment_name");

        fieldTypeInTable.add(TypeOfFiled.STRING);
        fieldNameInTable.add("experiment_type");

        fieldTypeInTable.add(TypeOfFiled.DATATIME);
        fieldNameInTable.add("start_time");

        fieldTypeInTable.add(TypeOfFiled.DATATIME);
        fieldNameInTable.add("end_time");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("step_interval_ms");

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

    protected Experiment getExperimentFromData(List<Object> dataList) {
        String type = (String) dataList.get(2);
        Experiment result = ExperimentFactory.getExperimentByType(type);
        if (dataList.size() != getQuantityOfFields()) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Wrong data. Data size - " + dataList.size() + " ; " +
                    "Quantity - " + getQuantityOfFields());
            throw new WrongDataDaoException("Data size - " + dataList.size() + " ; " +
                    "Quantity - " + getQuantityOfFields());
        }

        result.setIdExperiment((Integer) dataList.get(0));
        result.setExperimentName(dataList.get(1) == null ? "" : (String) dataList.get(1));
        // type skip dataList.get(2)
        result.setStartTime(dataList.get(3) == null ? new DateTime(0) : new DateTime(dataList.get(3)));
        result.setEndTime(dataList.get(4) == null ? new DateTime(0) : new DateTime(dataList.get(4)));
        result.setStepIntervalMs((Integer) dataList.get(5));
        result.setCreated(dataList.get(6) == null ? new DateTime(0) : new DateTime(dataList.get(6)));
        result.setUpdated(dataList.get(7) == null ? new DateTime(0) : new DateTime(dataList.get(7)));

        return result;
    }

    protected List<Object> getDataFromExperiment(Experiment experiment) {
        List<Object> resultList = new ArrayList<Object>();

        resultList.add(experiment.getIdExperiment());
        resultList.add(experiment.getExperimentName());
        resultList.add(experiment.getExperimentType());
        resultList.add(experiment.getStartTime());
        resultList.add(experiment.getEndTime());
        resultList.add(experiment.getStepIntervalMs());
        resultList.add(experiment.getCreated());
        resultList.add(experiment.getUpdated());

        return resultList;
    }
}
