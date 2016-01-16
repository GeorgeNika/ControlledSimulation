package ua.george_nika.simulation.dao.experiment;

import ua.george_nika.simulation.model.experiment.Experiment;

/**
 * Created by george on 03.12.2015.
 */
public interface ExperimentExtraDao {

    void addExtraDataToExperiment(Experiment experiment);

    void createNewExperimentExtraDataById(int idExperiment);

    void updateExperimentExtraData(Experiment experiment);

    void deleteExperimentExtraData(int idExperiment);

}
