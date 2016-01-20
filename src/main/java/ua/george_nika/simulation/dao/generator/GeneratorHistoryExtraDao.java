package ua.george_nika.simulation.dao.generator;

import ua.george_nika.simulation.model.generator.GeneratorHistory;

/**
 * Created by george on 11.01.2016.
 */
public interface GeneratorHistoryExtraDao {

    void createNewGeneratorHistoryExtraRecordById(int idGeneratorHistory);

    void saveGeneratorHistoryExtraData(GeneratorHistory generatorHistory);

    void addExtraDataToGeneratorHistory(GeneratorHistory generatorHistory);
}
