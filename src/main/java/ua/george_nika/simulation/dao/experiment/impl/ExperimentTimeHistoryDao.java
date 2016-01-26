/**
 * work`s with "time" experiment history table
 */

package ua.george_nika.simulation.dao.experiment.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.dao.experiment.abstr.AbstractExperimentHistoryExtraDao;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.model.experiment.impl.TimeExperiment;
import ua.george_nika.simulation.model.experiment.impl.TimeExperimentHistory;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

@Repository
public class ExperimentTimeHistoryDao extends AbstractExperimentHistoryExtraDao {

    private static final String TABLE_NAME = "experiment_time_history";
    private static final String ID_NAME = "id_experiment_history";
    private static final int QUANTITY_OF_FIELDS = 6;

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = ExperimentTimeHistoryDao.class.getSimpleName();

    static {
        DaoFactory.registerExperimentHistoryExtraClassInFactory(TimeExperiment.EXPERIMENT_TYPE,
                ExperimentTimeHistoryDao.class.getCanonicalName());
    }

    public ExperimentTimeHistoryDao() {
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.LONG);
        fieldNameInTable.add("min_time_ms");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("id_start_generator");

        fieldTypeInTable.add(TypeOfFiled.STRING);
        fieldNameInTable.add("start_generator_name");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("id_end_generator");

        fieldTypeInTable.add(TypeOfFiled.STRING);
        fieldNameInTable.add("end_generator_name");
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
    protected void fillExtraDataInExperimentHistory(List<Object> dataList, ExperimentHistory experimentHistory) {
        if (dataList.size() != getQuantityOfFields()) {
            throw new WrongDataDaoException(LOGGER_NAME, CLASS_NAME, "Wrong data. Data size - " + dataList.size()
                    + " ; " + "Quantity - " + getQuantityOfFields(), new RuntimeException());
        }

        TimeExperimentHistory timeExperimentHistory =
                ClassTypeUtil.getCheckedClass(experimentHistory, TimeExperimentHistory.class);
        timeExperimentHistory.setIdExperiment((Integer) dataList.get(0));
        timeExperimentHistory.setMinTimeMs((Long) dataList.get(1));
        timeExperimentHistory.setIdStartGenerator((Integer) dataList.get(2));
        timeExperimentHistory.setStartGeneratorName((String) dataList.get(3));
        timeExperimentHistory.setIdEndGenerator((Integer) dataList.get(4));
        timeExperimentHistory.setEndGeneratorName((String) dataList.get(5));

    }

    @Override
    protected List<Object> getExtraDataFromExperimentHistory(ExperimentHistory experimentHistory) {
        List<Object> resultList = new ArrayList<>();

        TimeExperimentHistory timeExperimentHistory =
                ClassTypeUtil.getCheckedClass(experimentHistory, TimeExperimentHistory.class);
        resultList.add(timeExperimentHistory.getIdExperiment());
        resultList.add(timeExperimentHistory.getMinTimeMs());
        resultList.add(timeExperimentHistory.getIdStartGenerator());
        resultList.add(timeExperimentHistory.getStartGeneratorName());
        resultList.add(timeExperimentHistory.getIdEndGenerator());
        resultList.add(timeExperimentHistory.getEndGeneratorName());

        return resultList;
    }

}
