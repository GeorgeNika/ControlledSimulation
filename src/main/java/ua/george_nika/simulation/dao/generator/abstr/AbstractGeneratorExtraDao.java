/**
 * Base methods for work with generator extra table
 */

package ua.george_nika.simulation.dao.generator.abstr;

import ua.george_nika.simulation.dao.generator.GeneratorExtraDao;
import ua.george_nika.simulation.model.generator.Generator;

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
