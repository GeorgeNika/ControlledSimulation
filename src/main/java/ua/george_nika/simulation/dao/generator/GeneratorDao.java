/**
 * Work with generator common table
 */
package ua.george_nika.simulation.dao.generator;

import ua.george_nika.simulation.model.generator.Generator;

import java.util.List;

public interface GeneratorDao {

    Generator getLazyGeneratorById(int id);

    int createdEmptyGeneratorByTypeAndGetNewId(String generatorType);

    void updateLazyGenerator(Generator generator);

    void deleteLazyGeneratorById(int id);

    List<Generator> getAllLazyGenerator();

    List<Generator> getAllLazyGeneratorByExperimentId(int idExperiment);

}
