/**
 * Filter for experiment history records
 */
package ua.george_nika.simulation.dao.filter;

import ua.george_nika.simulation.dao.DaoConst;
import ua.george_nika.simulation.dao.error.WrongSetFieldDaoException;
import ua.george_nika.simulation.util.AppLog;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

public class ExperimentFilter implements DaoFilter {

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = ExperimentFilter.class.getSimpleName();

    protected int idExperiment;
    protected int idExperimentHistory;


    @Override
    public void setFilterInfo(List<String> filterFieldNameList, List<Integer> filterFiledValueList) {
        if (filterFieldNameList == null) {
            filterFieldNameList = new ArrayList<>();
        }
        if (filterFiledValueList == null) {
            filterFiledValueList = new ArrayList<>();
        }

        if (idExperiment != 0) {
            filterFieldNameList.add(DaoConst.FIELD_FOR_ID_EXPERIMENT_FILTER);
            filterFiledValueList.add(idExperiment);
        }

        if (idExperimentHistory != 0) {
            filterFieldNameList.add(DaoConst.FIELD_FOR_ID_EXPERIMENT_HISTORY_FILTER);
            filterFiledValueList.add(idExperimentHistory);
        }

        if (filterFieldNameList.size() != filterFiledValueList.size()) {
            throw new WrongSetFieldDaoException(LOGGER_NAME, CLASS_NAME, "Non equal size : name - "
                    + filterFieldNameList.size() + ", value - " + filterFiledValueList.size(), new RuntimeException());
        }
    }

    public int getIdExperiment() {
        return idExperiment;
    }

    public void setIdExperiment(int idExperiment) {
        this.idExperiment = idExperiment;
    }

    public int getIdExperimentHistory() {
        return idExperimentHistory;
    }

    public void setIdExperimentHistory(int idExperimentHistory) {
        this.idExperimentHistory = idExperimentHistory;
    }
}
