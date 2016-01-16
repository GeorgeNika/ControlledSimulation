package ua.george_nika.simulation.dao.generator;

import ua.george_nika.simulation.model.generator.Generator;

/**
 * Created by george on 08.12.2015.
 */
public interface GeneratorExtraDao {

    void addExtraDataToGenerator(Generator generator);

    void createNewExtraDataById(int idGenerator);
}
