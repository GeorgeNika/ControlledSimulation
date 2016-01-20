package ua.george_nika.simulation.dao.entity.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.entity.abstr.AbstractEntityHistoryExtraDao;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.model.entity.EntityHistory;
import ua.george_nika.simulation.model.entity.impl.BusEntity;
import ua.george_nika.simulation.model.entity.impl.BusHistory;
import ua.george_nika.simulation.model.entity.impl.BusRoundEntity;
import ua.george_nika.simulation.model.entity.impl.BusRoundHistory;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 04.12.2015.
 */
@Repository
public class EntityBusRoundHistoryDao extends EntityBusHistoryDao {

    static {
        DaoFactory.registerEntityHistoryExtraClassInFactory(BusRoundEntity.ENTITY_TYPE,
                EntityBusRoundHistoryDao.class.getCanonicalName());
    }
}
