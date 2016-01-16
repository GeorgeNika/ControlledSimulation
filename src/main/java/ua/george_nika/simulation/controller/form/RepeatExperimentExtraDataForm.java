package ua.george_nika.simulation.controller.form;

import ua.george_nika.simulation.model.experiment.impl.RepeatExperiment;

/**
 * Created by george on 25.12.2015.
 */
public class RepeatExperimentExtraDataForm {

    private int repeat;

    public RepeatExperimentExtraDataForm() {
    }

    public RepeatExperimentExtraDataForm(RepeatExperiment repeatExperiment) {
        this.repeat = repeatExperiment.getRepeat();
    }

    public void updateExtraDataExperiment(RepeatExperiment repeatExperiment) {
        repeatExperiment.setRepeat(repeat);
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }
}
