/**
 * Base methods for work with generator history common table
 */

package ua.george_nika.simulation.dao.generator.abstr;

import ua.george_nika.simulation.dao.AbstractDao;
import ua.george_nika.simulation.dao.DaoConst;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.filter.GeneratorFilter;
import ua.george_nika.simulation.dao.generator.GeneratorHistoryDao;
import ua.george_nika.simulation.model.generator.GeneratorHistory;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractGeneratorHistoryDao extends AbstractDao implements GeneratorHistoryDao {


    abstract protected GeneratorHistory getGeneratorHistoryFromData(List<Object> dataList);

    abstract protected List<Object> getDataFromGeneratorHistory(GeneratorHistory generatorHistory);

    @Override
    public int createNewGeneratorHistoryRecord(GeneratorHistory generatorHistory) {
        return createEmptyRecordWithOneFieldAndGetNewId(DaoConst.GEN_TYPE_IN_GEN_HISTORY_MAIN_TABLE,
                generatorHistory.getGeneratorType(), TypeOfFiled.STRING);
    }

    @Override
    public void saveGeneratorHistory(GeneratorHistory generatorHistory) {
        List<Object> resultData = getDataFromGeneratorHistory(generatorHistory);
        updateRecordDataById(generatorHistory.getIdGeneratorHistory(), resultData);
    }

    public GeneratorHistory getLazyGeneratorHistoryById(int idGeneratorHistory) {
        List<Object> resultData = getSingleRecordDataById(idGeneratorHistory);
        return getGeneratorHistoryFromData(resultData);
    }

    public List<GeneratorHistory> getAllLazyGeneratorHistory() {
        List<GeneratorHistory> resultGeneratorHistoryList = new ArrayList<>();
        List<List<Object>> resultDataListList = getAllRecordDataList();
        GeneratorHistory tempGeneratorHistory;
        for (List<Object> loopData : resultDataListList) {
            tempGeneratorHistory = getGeneratorHistoryFromData(loopData);
            resultGeneratorHistoryList.add(tempGeneratorHistory);
        }
        return resultGeneratorHistoryList;
    }

    public List<GeneratorHistory> getLazyGeneratorHistoryListByFilter(GeneratorFilter generatorFilter) {
        List<GeneratorHistory> resultGeneratorHistoryList = new ArrayList<>();
        List<List<Object>> resultDataListList = getRecordDataListByFilter(generatorFilter);
        GeneratorHistory tempGeneratorHistory;
        for (List<Object> loopData : resultDataListList) {
            tempGeneratorHistory = getGeneratorHistoryFromData(loopData);
            resultGeneratorHistoryList.add(tempGeneratorHistory);
        }
        return resultGeneratorHistoryList;
    }
}
