package ua.george_nika.simulation.dao.generator.abstr;

import ua.george_nika.simulation.dao.generator.GeneratorExtraDao;
import ua.george_nika.simulation.model.generator.Generator;

/**
 * Created by george on 12.01.2016.
 */
abstract public class AbstractGeneratorExtraDao implements GeneratorExtraDao{

    @Override
    public void createNewExtraDataById(int idGenerator) {
        //do nothing
    }

    @Override
    public void addExtraDataToGenerator(Generator generator) {
        //do nothing
    }
}
