package ua.george_nika.simulation.model.generator.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.GeneratorHistoryFactory;
import ua.george_nika.simulation.model.generator.abstr.AbstractGeneratorHistory;

/**
 * Created by george on 11.01.2016.
 */
@Component
public class StationGeneratorHistory extends AbstractGeneratorHistory {

    static {
        GeneratorHistoryFactory.registerClassInFactory(StationGenerator.GENERATOR_TYPE,
                StationGeneratorHistory.class.getCanonicalName());
    }



    @Override
    protected void updateGeneratorHistoryExtraData(Generator generator) {
        // todo
    }

    @Override
    public String getRunInfoString() {
        //todo
        return "here must be info about working generator";
    }

    @Override
    protected void setInitialGeneratorHistoryExtraData(Generator generator) {

    }
}
