/**
 * work`s with "time" experiment table
 */

package ua.george_nika.simulation.dao.experiment.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.dao.experiment.abstr.AbstractExperimentExtraDao;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.impl.TimeExperiment;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

@Repository
public class ExperimentTimeDao extends AbstractExperimentExtraDao {

    private static final String TABLE_NAME = "experiment_time";
    private static final String ID_NAME = "id_experiment";
    private static final int QUANTITY_OF_FIELDS = 3;


    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = ExperimentTimeDao.class.getSimpleName();

    static {
        DaoFactory.registerExperimentExtraClassInFactory(TimeExperiment.EXPERIMENT_TYPE,
                ExperimentTimeDao.class.getCanonicalName());
    }

    public ExperimentTimeDao() {
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("id_start_generator");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("id_end_generator");
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
    protected void fillExtraDataInExperiment(List<Object> dataList, Experiment experiment) {
        if (dataList.size() != getQuantityOfFields()) {
            throw new WrongDataDaoException(LOGGER_NAME, CLASS_NAME, "Wrong data. Data size - " + dataList.size()
                    + " ; " + "Quantity - " + getQuantityOfFields(), new RuntimeException());
        }

        TimeExperiment timeExperiment = ClassTypeUtil.getCheckedClass(experiment, TimeExperiment.class);
        timeExperiment.setIdExperiment((Integer) dataList.get(0));
        timeExperiment.setIdStartGenerator((Integer) dataList.get(1));
        timeExperiment.setIdEndGenerator((Integer) dataList.get(2));

    }

    protected List<Object> getExtraDataFromExperiment(Experiment experiment) {
        List<Object> resultList = new ArrayList<>();

        TimeExperiment timeExperiment = ClassTypeUtil.getCheckedClass(experiment, TimeExperiment.class);
        resultList.add(timeExperiment.getIdExperiment());
        resultList.add(timeExperiment.getIdStartGenerator());
        resultList.add(timeExperiment.getIdEndGenerator());

        return resultList;
    }
}
