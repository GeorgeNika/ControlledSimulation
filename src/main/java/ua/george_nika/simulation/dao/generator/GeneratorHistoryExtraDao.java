/**
 * Work with generator history extra tables
 */

package ua.george_nika.simulation.dao.generator;

import ua.george_nika.simulation.model.generator.GeneratorHistory;

public interface GeneratorHistoryExtraDao {

    void createNewGeneratorHistoryExtraRecordById(int idGeneratorHistory);

    void saveGeneratorHistoryExtraData(GeneratorHistory generatorHistory);

    void addExtraDataToGeneratorHistory(GeneratorHistory generatorHistory);
}
