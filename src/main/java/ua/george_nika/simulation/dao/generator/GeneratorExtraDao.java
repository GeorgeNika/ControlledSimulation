/**
 * Work with generator extra tables
 */

package ua.george_nika.simulation.dao.generator;

import ua.george_nika.simulation.model.generator.Generator;

@SuppressWarnings("unused")

public interface GeneratorExtraDao {

    void addExtraDataToGenerator(Generator generator);

    void createNewExtraDataById(int idGenerator);
}
