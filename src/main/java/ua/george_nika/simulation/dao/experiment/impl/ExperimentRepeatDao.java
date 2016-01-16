package ua.george_nika.simulation.dao.experiment.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.experiment.abstr.AbstractExperimentExtraDao;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.impl.RepeatExperiment;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 04.12.2015.
 */
@Repository
public class ExperimentRepeatDao extends AbstractExperimentExtraDao {

    private static final String TABLE_NAME = "experiment_repeat";
    private static final String ID_NAME = "id_experiment";
    private static final int QUANTITY_OF_FIELDS = 2;


    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = ExperimentRepeatDao.class.getSimpleName();

    static {
        DaoFactory.registerExperimentExtraClassInFactory(RepeatExperiment.EXPERIMENT_TYPE,
                ExperimentRepeatDao.class.getCanonicalName());
    }
    public ExperimentRepeatDao(){
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("repeat");
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
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Wrong data. Data size - " + dataList.size() + " ; " +
                    "Quantity - " + getQuantityOfFields());
            throw new WrongDataDaoException("Data size - " + dataList.size() + " ; " +
                    "Quantity - " + getQuantityOfFields());
        }

        RepeatExperiment repeatExperiment = ClassTypeUtil.getCheckedClass(experiment, RepeatExperiment.class);
        repeatExperiment.setIdExperiment((Integer) dataList.get(0));
        repeatExperiment.setRepeat((Integer) dataList.get(1));
    }

    protected List<Object> getExtraDataFromExperiment(Experiment experiment) {
        List<Object> resultList = new ArrayList<>();

        RepeatExperiment repeatExperiment = ClassTypeUtil.getCheckedClass(experiment, RepeatExperiment.class);
        resultList.add(repeatExperiment.getIdExperiment());
        resultList.add(repeatExperiment.getRepeat());

        return resultList;
    }
}
