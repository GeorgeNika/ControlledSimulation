package ua.george_nika.simulation.model.experiment.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.experiment.abstr.AbstractExperiment;
import ua.george_nika.simulation.model.experiment.ExperimentFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by george on 23.11.2015.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class RepeatExperiment extends AbstractExperiment {

    public static final String EXPERIMENT_TYPE = "repeat";

    private int repeat;

    static {
        ExperimentFactory.registerClassInFactory(EXPERIMENT_TYPE, RepeatExperiment.class.getCanonicalName());
    }

    public String getExperimentType() {
        return EXPERIMENT_TYPE;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }
}
