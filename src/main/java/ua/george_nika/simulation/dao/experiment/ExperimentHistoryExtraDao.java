package ua.george_nika.simulation.dao.experiment;

import ua.george_nika.simulation.model.experiment.ExperimentHistory;

/**
 * Created by george on 11.01.2016.
 */
public interface ExperimentHistoryExtraDao {

    void createNewExperimentHistoryExtraRecordById(int idExperimentHistory);

    void saveExperimentHistoryExtraData(ExperimentHistory experimentHistory);

    void addExtraDataToExperimentHistory(ExperimentHistory experimentHistory);
}
