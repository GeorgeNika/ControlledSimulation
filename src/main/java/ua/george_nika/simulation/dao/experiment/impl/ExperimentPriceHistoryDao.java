/**
 * work`s with "price" experiment history table
 */

package ua.george_nika.simulation.dao.experiment.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.dao.experiment.abstr.AbstractExperimentHistoryExtraDao;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.model.experiment.impl.PriceExperiment;
import ua.george_nika.simulation.model.experiment.impl.PriceExperimentHistory;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

@Repository
public class ExperimentPriceHistoryDao extends AbstractExperimentHistoryExtraDao {

    private static final String TABLE_NAME = "experiment_price_history";
    private static final String ID_NAME = "id_experiment_history";
    private static final int QUANTITY_OF_FIELDS = 2;


    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = ExperimentPriceHistoryDao.class.getSimpleName();

    static {
        DaoFactory.registerExperimentHistoryExtraClassInFactory(PriceExperiment.EXPERIMENT_TYPE,
                ExperimentPriceHistoryDao.class.getCanonicalName());
    }

    public ExperimentPriceHistoryDao() {
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("current_price");
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

        PriceExperimentHistory priceExperimentHistory =
                ClassTypeUtil.getCheckedClass(experimentHistory, PriceExperimentHistory.class);
        priceExperimentHistory.setIdExperiment((Integer) dataList.get(0));
        priceExperimentHistory.setPrice((Integer) dataList.get(1));

    }

    @Override
    protected List<Object> getExtraDataFromExperimentHistory(ExperimentHistory experimentHistory) {
        List<Object> resultList = new ArrayList<>();

        PriceExperimentHistory priceExperimentHistory =
                ClassTypeUtil.getCheckedClass(experimentHistory, PriceExperimentHistory.class);
        resultList.add(priceExperimentHistory.getIdExperiment());
        resultList.add(priceExperimentHistory.getPrice());

        return resultList;
    }
}
