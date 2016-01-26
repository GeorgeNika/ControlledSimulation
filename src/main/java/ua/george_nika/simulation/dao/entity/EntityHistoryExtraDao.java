/**
 * Work with entity history extra tables
 */

package ua.george_nika.simulation.dao.entity;

import ua.george_nika.simulation.model.entity.EntityHistory;

public interface EntityHistoryExtraDao {

    void createNewEntityHistoryExtraRecordById(int idEntityHistory);

    void saveEntityHistoryExtraData(EntityHistory entityHistory);

    void addExtraDataToEntityHistory(EntityHistory entityHistory);
}
