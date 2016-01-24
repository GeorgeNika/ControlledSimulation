package ua.george_nika.simulation.dao.experiment.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.dao.experiment.abstr.AbstractExperimentExtraDao;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.impl.PriceExperiment;
import ua.george_nika.simulation.model.experiment.impl.RepeatExperiment;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 04.12.2015.
 */
@Repository
public class ExperimentPriceDao extends AbstractExperimentExtraDao {

    private static final String TABLE_NAME = "experiment_price";
    private static final String ID_NAME = "id_experiment";
    private static final int QUANTITY_OF_FIELDS = 5;


    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = ExperimentPriceDao.class.getSimpleName();

    static {
        DaoFactory.registerExperimentExtraClassInFactory(PriceExperiment.EXPERIMENT_TYPE,
                ExperimentPriceDao.class.getCanonicalName());
    }
    public ExperimentPriceDao(){
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("min_price");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("max_price");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("repeat");

        fieldTypeInTable.add(TypeOfFiled.BOOLEAN);
        fieldNameInTable.add("zero_variation");
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

        PriceExperiment priceExperiment = ClassTypeUtil.getCheckedClass(experiment, PriceExperiment.class);
        priceExperiment.setIdExperiment((Integer) dataList.get(0));
        priceExperiment.setMinPrice((Integer) dataList.get(1));
        priceExperiment.setMaxPrice((Integer) dataList.get(2));
        priceExperiment.setRepeat((Integer) dataList.get(3));
        priceExperiment.setZeroVariation((Boolean) dataList.get(4));
    }

    protected List<Object> getExtraDataFromExperiment(Experiment experiment) {
        List<Object> resultList = new ArrayList<>();

        PriceExperiment priceExperiment = ClassTypeUtil.getCheckedClass(experiment, PriceExperiment.class);
        resultList.add(priceExperiment.getIdExperiment());
        resultList.add(priceExperiment.getMinPrice());
        resultList.add(priceExperiment.getMaxPrice());
        resultList.add(priceExperiment.getRepeat());
        resultList.add(priceExperiment.isZeroVariation());

        return resultList;
    }
}
