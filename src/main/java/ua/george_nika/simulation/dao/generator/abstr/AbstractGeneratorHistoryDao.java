package ua.george_nika.simulation.dao.generator.abstr;

import ua.george_nika.simulation.dao.AbstractDao;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.generator.GeneratorHistoryDao;
import ua.george_nika.simulation.model.generator.GeneratorHistory;
import ua.george_nika.simulation.util.AppConst;
import ua.george_nika.simulation.util.AppLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 12.01.2016.
 */
abstract public class AbstractGeneratorHistoryDao extends AbstractDao implements GeneratorHistoryDao {



    abstract protected GeneratorHistory getGeneratorHistoryFromData(List<Object> dataList);

    abstract protected List<Object> getDataFromGeneratorHistory(GeneratorHistory generatorHistory);

    @Override
    public int createNewGeneratorHistoryRecord(GeneratorHistory generatorHistory) {
        int resultId = createEmptyRecordWithOneFieldAndGetNewId(
                AppConst.GEN_TYPE_IN_GEN_HISTORY_MAIN_TABLE, generatorHistory.getGeneratorType(), TypeOfFiled.STRING);
        return resultId;
    }

    @Override
    public void saveGeneratorHistory(GeneratorHistory generatorHistory) {
        List<Object> resultData = getDataFromGeneratorHistory(generatorHistory);
        updateRecordDataById(generatorHistory.getIdGeneratorHistory(),resultData);
    }

    public GeneratorHistory getLazyGeneratorHistoryById(int idGeneratorHistory) {
        List<Object> resultData = getSingleRecordDataById(idGeneratorHistory);
        GeneratorHistory resultGeneratorHistory = getGeneratorHistoryFromData(resultData);
        return resultGeneratorHistory;
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
}
