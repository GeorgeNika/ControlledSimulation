/**
 * work`s with "simple" experiment table
 */

package ua.george_nika.simulation.dao.experiment.impl;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.experiment.abstr.AbstractExperimentExtraDao;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.impl.SimpleExperiment;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

@Repository
public class ExperimentSimpleDao extends AbstractExperimentExtraDao {

    private static final String TABLE_NAME = "experiment_simple";
    private static final String ID_NAME = "id_experiment";
    private static final int QUANTITY_OF_FIELDS = 5;

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = ExperimentSimpleDao.class.getSimpleName();

    static {
        DaoFactory.registerExperimentExtraClassInFactory(SimpleExperiment.EXPERIMENT_TYPE,
                ExperimentSimpleDao.class.getCanonicalName());
    }

    public ExperimentSimpleDao() {
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.STRING);
        fieldNameInTable.add("info_string");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("info_int");

        fieldTypeInTable.add(TypeOfFiled.LONG);
        fieldNameInTable.add("info_long");

        fieldTypeInTable.add(TypeOfFiled.DATATIME);
        fieldNameInTable.add("info_start_time");
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

        SimpleExperiment simpleExperiment = ClassTypeUtil.getCheckedClass(experiment, SimpleExperiment.class);

        simpleExperiment.setIdExperiment((Integer) dataList.get(0));
        simpleExperiment.setInfoString(dataList.get(1) == null ? "" : (String) dataList.get(1));
        simpleExperiment.setInfoInt((Integer) dataList.get(2));
        simpleExperiment.setInfoLong((Long) dataList.get(3));
        simpleExperiment.setInfoStartTime(dataList.get(4) == null ? new DateTime(0) : new DateTime(dataList.get(4)));
    }


    protected List<Object> getExtraDataFromExperiment(Experiment experiment) {
        List<Object> resultList = new ArrayList<>();

        SimpleExperiment simpleExperiment = ClassTypeUtil.getCheckedClass(experiment, SimpleExperiment.class);
        resultList.add(simpleExperiment.getIdExperiment());
        resultList.add(simpleExperiment.getInfoString());
        resultList.add(simpleExperiment.getInfoInt());
        resultList.add(simpleExperiment.getInfoLong());
        resultList.add(simpleExperiment.getInfoStartTime());

        return resultList;
    }
}
