/**
 * Work with "bus round" entity history table
 */

package ua.george_nika.simulation.dao.entity.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.model.entity.impl.BusRoundEntity;

@Repository
public class EntityBusRoundHistoryDao extends EntityBusHistoryDao {

    static {
        DaoFactory.registerEntityHistoryExtraClassInFactory(BusRoundEntity.ENTITY_TYPE,
                EntityBusRoundHistoryDao.class.getCanonicalName());
    }
}
