package ua.george_nika.simulation.model.entity.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.entity.EntityHistoryFactory;
import ua.george_nika.simulation.model.entity.abstr.AbstractEntityHistory;
import ua.george_nika.simulation.model.generator.Generator;

/**
 * Created by george on 11.01.2016.
 */
@Component
public class BusHistory extends AbstractEntityHistory {

    static {
        EntityHistoryFactory.registerClassInFactory(BusEntity.ENTITY_TYPE, BusHistory.class.getCanonicalName());
    }


    @Override
    protected void setInitialEntityHistoryExtraData(Generator generator, Entity entity) {
        // todo
    }

    @Override
    protected void updateEntityHistoryExtraData(Entity entity) {
        // todo
    }
}
