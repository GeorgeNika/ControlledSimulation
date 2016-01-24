package ua.george_nika.simulation.dao.entity;

import ua.george_nika.simulation.model.entity.EntityHistory;

/**
 * Created by george on 11.01.2016.
 */
public interface EntityHistoryExtraDao {

    void createNewEntityHistoryExtraRecordById(int idEntityHistory);

    void saveEntityHistoryExtraData(EntityHistory entityHistory);

    void addExtraDataToEntityHistory(EntityHistory entityHistory);
}
