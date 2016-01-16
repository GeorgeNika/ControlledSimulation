package ua.george_nika.simulation.dao.experiment;

import ua.george_nika.simulation.model.experiment.Experiment;

import java.util.List;

/**
 * Created by george on 03.12.2015.
 */
public interface ExperimentDao {

    Experiment getLazyExperimentById(int id);

    int createEmptyExperimentByTypeAndGetNewId(String experimentType);

    void updateLazyExperiment(Experiment experiment);

    void changeExperimentType(int idExperiment, String newExperimentType);

    void deleteLazyExperimentById(int id);

    List<Experiment> getAllLazyExperiment();

    void addGeneratorToExperimentList(int idExperiment, int idGenerator);

    void deleteGeneratorFromExperimentList(int idExperiment, int idGenerator);
}
