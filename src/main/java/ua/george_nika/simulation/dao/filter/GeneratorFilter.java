package ua.george_nika.simulation.dao.filter;

import ua.george_nika.simulation.dao.DaoConst;
import ua.george_nika.simulation.dao.error.WrongSetFieldDaoException;
import ua.george_nika.simulation.util.AppLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 21.01.2016.
 */
public class GeneratorFilter implements DaoFilter {

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = GeneratorFilter.class.getSimpleName();

    protected int idExperiment;
    protected int idExperimentHistory;
    protected int idGenerator;
    protected int idGeneratorHistory;


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

        if (idGenerator != 0) {
            filterFieldNameList.add(DaoConst.FIELD_FOR_ID_GENERATOR_FILTER);
            filterFiledValueList.add(idGenerator);
        }

        if (idGeneratorHistory != 0) {
            filterFieldNameList.add(DaoConst.FIELD_FOR_ID_GENERATOR_HISTORY_FILTER);
            filterFiledValueList.add(idGeneratorHistory);
        }

        if (filterFieldNameList.size() != filterFiledValueList.size()) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Non equal size : name - " + filterFieldNameList.size()
                    + ", value - " + filterFiledValueList.size());
            throw new WrongSetFieldDaoException("Non equal size : name - " + filterFieldNameList.size()
                    + ", value - " + filterFiledValueList.size());
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

    public int getIdGenerator() {
        return idGenerator;
    }

    public void setIdGenerator(int idGenerator) {
        this.idGenerator = idGenerator;
    }

    public int getIdGeneratorHistory() {
        return idGeneratorHistory;
    }

    public void setIdGeneratorHistory(int idGeneratorHistory) {
        this.idGeneratorHistory = idGeneratorHistory;
    }
}
