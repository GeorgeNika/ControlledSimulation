/**
 * Work with generator history common table
 */

package ua.george_nika.simulation.dao.generator;

import ua.george_nika.simulation.dao.filter.GeneratorFilter;
import ua.george_nika.simulation.model.generator.GeneratorHistory;

import java.util.List;

public interface GeneratorHistoryDao {

    int createNewGeneratorHistoryRecord(GeneratorHistory generatorHistory);

    void saveGeneratorHistory(GeneratorHistory generatorHistory);

    GeneratorHistory getLazyGeneratorHistoryById(int idGeneratorHistory);

    List<GeneratorHistory> getAllLazyGeneratorHistory();

    List<GeneratorHistory> getLazyGeneratorHistoryListByFilter(GeneratorFilter generatorFilter);

}
