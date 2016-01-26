/**
 * Filter for entity history records
 */

package ua.george_nika.simulation.dao.filter;

import ua.george_nika.simulation.dao.DaoConst;
import ua.george_nika.simulation.dao.error.WrongSetFieldDaoException;
import ua.george_nika.simulation.util.AppLog;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

public class EntityFilter implements DaoFilter {

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = EntityFilter.class.getSimpleName();

    protected int idExperiment;
    protected int idExperimentHistory;
    protected int idGenerator;
    protected int idGeneratorHistory;
    protected int idEntityHistory;
    protected int idEntity;


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

        if (idEntity != 0) {
            filterFieldNameList.add(DaoConst.FIELD_FOR_ID_ENTITY_FILTER);
            filterFiledValueList.add(idEntity);
        }

        if (idEntityHistory != 0) {
            filterFieldNameList.add(DaoConst.FIELD_FOR_ID_ENTITY_HISTORY_FILTER);
            filterFiledValueList.add(idEntityHistory);
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

    public int getIdEntityHistory() {
        return idEntityHistory;
    }

    public void setIdEntityHistory(int idEntityHistory) {
        this.idEntityHistory = idEntityHistory;
    }

    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
    }
}
