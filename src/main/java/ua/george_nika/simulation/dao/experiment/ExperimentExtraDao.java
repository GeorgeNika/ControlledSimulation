/**
 * Work with experiment extra tables
 */

package ua.george_nika.simulation.dao.experiment;

import ua.george_nika.simulation.model.experiment.Experiment;

public interface ExperimentExtraDao {

    void addExtraDataToExperiment(Experiment experiment);

    void createNewExperimentExtraDataById(int idExperiment);

    void updateExperimentExtraData(Experiment experiment);

    void deleteExperimentExtraData(int idExperiment);

}
