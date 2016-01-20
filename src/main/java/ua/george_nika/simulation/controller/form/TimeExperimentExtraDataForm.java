/**
 * springMVC form
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.form;

import ua.george_nika.simulation.model.experiment.impl.TimeExperiment;

@SuppressWarnings("unused")

public class TimeExperimentExtraDataForm {

    private int idStartGenerator;
    private int idEndGenerator;

    public TimeExperimentExtraDataForm() {
    }

    public TimeExperimentExtraDataForm(TimeExperiment timeExperiment) {
        this.idStartGenerator = timeExperiment.getIdStartGenerator();
        this.idEndGenerator = timeExperiment.getIdEndGenerator();
    }

    public void updateExtraDataExperiment(TimeExperiment timeExperiment) {
        timeExperiment.setIdStartGenerator(idStartGenerator);
        timeExperiment.setIdEndGenerator(idEndGenerator);
    }

    public int getIdStartGenerator() {
        return idStartGenerator;
    }

    public void setIdStartGenerator(int idStartGenerator) {
        this.idStartGenerator = idStartGenerator;
    }

    public int getIdEndGenerator() {
        return idEndGenerator;
    }

    public void setIdEndGenerator(int idEndGenerator) {
        this.idEndGenerator = idEndGenerator;
    }
}
