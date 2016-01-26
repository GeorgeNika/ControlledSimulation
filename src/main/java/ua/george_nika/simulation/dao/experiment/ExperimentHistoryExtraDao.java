/**
 * Work with experiment history extra tables
 */

package ua.george_nika.simulation.dao.experiment;

import ua.george_nika.simulation.model.experiment.ExperimentHistory;

public interface ExperimentHistoryExtraDao {

    void createNewExperimentHistoryExtraRecordById(int idExperimentHistory);

    void saveExperimentHistoryExtraData(ExperimentHistory experimentHistory);

    void addExtraDataToExperimentHistory(ExperimentHistory experimentHistory);
}
