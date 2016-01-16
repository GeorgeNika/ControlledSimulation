package ua.george_nika.simulation.controller.form;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import ua.george_nika.simulation.model.experiment.impl.SimpleExperiment;
import ua.george_nika.simulation.model.experiment.impl.TimeExperiment;
import ua.george_nika.simulation.util.AppConst;

/**
 * Created by george on 25.12.2015.
 */
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
