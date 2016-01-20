package ua.george_nika.simulation.dao.generator;

import ua.george_nika.simulation.model.generator.GeneratorHistory;

import java.util.List;

/**
 * Created by george on 05.01.2016.
 */
public interface GeneratorHistoryDao {

    int createNewGeneratorHistoryRecord(GeneratorHistory generatorHistory);

    void saveGeneratorHistory(GeneratorHistory generatorHistory);

    GeneratorHistory getLazyGeneratorHistoryById(int idGeneratorHistory);

    List<GeneratorHistory> getAllLazyGeneratorHistory();

    List<GeneratorHistory> getAllLazyGeneratorHistoryByExperiment(int idExperimentHistory);

}
