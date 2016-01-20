package ua.george_nika.simulation.model.entity.impl;

import org.joda.time.MutableDateTime;
import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.entity.EntityHistoryFactory;
import ua.george_nika.simulation.model.entity.abstr.AbstractEntityHistory;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.util.ClassTypeUtil;

/**
 * Created by george on 11.01.2016.
 */
@Component
public class BusRoundHistory extends BusHistory {

    static {
        EntityHistoryFactory.registerClassInFactory(BusRoundEntity.ENTITY_TYPE,
                BusRoundHistory.class.getCanonicalName());
    }

}
