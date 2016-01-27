/**
 * Special variables and methods for "round_bus" entity history
 */

package ua.george_nika.simulation.model.entity.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.EntityHistoryFactory;

@Component
public class RoundBusHistory extends BusHistory {

    static {
        EntityHistoryFactory.registerClassInFactory(RoundBusEntity.ENTITY_TYPE,
                RoundBusHistory.class.getCanonicalName());
    }

}
