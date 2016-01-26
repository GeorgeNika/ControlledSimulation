/**
 * Base methods for work with generator history extra table
 */

package ua.george_nika.simulation.dao.generator.abstr;

import ua.george_nika.simulation.dao.AbstractDao;
import ua.george_nika.simulation.dao.generator.GeneratorHistoryExtraDao;
import ua.george_nika.simulation.model.generator.GeneratorHistory;

import java.util.List;

abstract public class AbstractGeneratorHistoryExtraDao extends AbstractDao implements GeneratorHistoryExtraDao {

    abstract protected void fillExtraDataInGeneratorHistory(List<Object> dataList, GeneratorHistory generatorHistory);

    abstract protected List<Object> getExtraDataFromGeneratorHistory(GeneratorHistory generatorHistory);

    @Override
    public void createNewGeneratorHistoryExtraRecordById(int idGeneratorHistory) {
        createEmptyRecordWithId(idGeneratorHistory);
    }

    @Override
    public void saveGeneratorHistoryExtraData(GeneratorHistory generatorHistory) {
        List<Object> resultData = getExtraDataFromGeneratorHistory(generatorHistory);
        updateRecordDataById(generatorHistory.getIdGeneratorHistory(), resultData);
    }

    public void addExtraDataToGeneratorHistory(GeneratorHistory generatorHistory) {
        List<Object> resultData = getSingleRecordDataById(generatorHistory.getIdGeneratorHistory());
        fillExtraDataInGeneratorHistory(resultData, generatorHistory);
    }
}
