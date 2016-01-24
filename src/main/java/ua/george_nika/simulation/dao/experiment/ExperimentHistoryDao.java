package ua.george_nika.simulation.dao.experiment;

import ua.george_nika.simulation.dao.filter.ExperimentFilter;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;

import java.util.List;

/**
 * Created by george on 05.01.2016.
 */
public interface ExperimentHistoryDao {

    int createNewExperimentHistoryRecord(ExperimentHistory experimentHistory);

    void saveExperimentHistory(ExperimentHistory experimentHistory);

    ExperimentHistory getLazyExperimentHistoryById(int idExperimentHistory);

    List<ExperimentHistory> getAllLazyExperimentHistory();

    List<ExperimentHistory> getLazyExperimentHistoryListByFilter(ExperimentFilter experimentFilter);

    void deleteExperimentHistoryById(int id);

}
