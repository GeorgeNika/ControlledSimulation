/**
 * springMVC form
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.form;

import ua.george_nika.simulation.model.experiment.impl.RepeatExperiment;

@SuppressWarnings("unused")

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
