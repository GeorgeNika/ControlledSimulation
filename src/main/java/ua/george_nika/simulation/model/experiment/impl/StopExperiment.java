/**
 * Special variables and methods for "price" experiment
 * do our experiment 1 time with pause after each iteration
 */

package ua.george_nika.simulation.model.experiment.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.experiment.ExperimentFactory;
import ua.george_nika.simulation.model.experiment.abstr.AbstractExperiment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class StopExperiment extends AbstractExperiment {

    public static final String EXPERIMENT_TYPE = "stop";

    static {
        ExperimentFactory.registerClassInFactory(EXPERIMENT_TYPE, StopExperiment.class.getCanonicalName());
    }

    public String getExperimentType() {
        return EXPERIMENT_TYPE;
    }

    public void run() {
        while (running.get()) {

            if (currentTime.isEqual(endTime)) {
                endExecution();
                continue;
            }

            if (paused.get()) {
                executePause();
                continue;
            }

            setNewExperimentTime();
            executeMainActionForAllGenerator();
            executeDependentActionForAllGenerator();
            updateAllHistory();
            setNewCurrentTimeForAllGenerator();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                endExecution();
            }

            paused.set(true);
        }
    }

}
