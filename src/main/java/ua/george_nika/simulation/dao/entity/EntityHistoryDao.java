/**
 * Work with entity history common table
 */

package ua.george_nika.simulation.dao.entity;

import ua.george_nika.simulation.dao.filter.EntityFilter;
import ua.george_nika.simulation.model.entity.EntityHistory;

import java.util.List;

public interface EntityHistoryDao {

    int createNewEntityHistoryRecord(EntityHistory entityHistory);

    void saveEntityHistory(EntityHistory entityHistory);

    EntityHistory getLazyEntityHistoryById(int idEntityHistory);

    List<EntityHistory> getAllLazyEntityHistory();

    List<EntityHistory> getLazyEntityHistoryListByFilter(EntityFilter entityFilter);
}




