/**
 * Special variables and methods for "repeat" experiment
 * repeat experiment N times
 */

package ua.george_nika.simulation.model.experiment.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.experiment.abstr.AbstractExperiment;
import ua.george_nika.simulation.model.experiment.ExperimentFactory;
import ua.george_nika.simulation.service.experiment.ExperimentHistoryService;
import ua.george_nika.simulation.util.AppLog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class RepeatExperiment extends AbstractExperiment {

    public static final String EXPERIMENT_TYPE = "repeat";

    private int repeat;

    @XmlTransient
    private int repeatLeft;

    static {
        ExperimentFactory.registerClassInFactory(EXPERIMENT_TYPE, RepeatExperiment.class.getCanonicalName());
    }

    @Override
    public void startExecution() {
        repeatLeft = repeat;
        standardExperimentStart();
    }

    protected void standardExperimentStart() {
        if (repeatLeft > 0) {
            repeatLeft--;
            super.startExecution();
        } else {
            working.set(false);
        }
    }

    @Override
    public void endExecution() {
        AppLog.info(experimentHistory.getLoggerName(), experimentHistory.getLogIdentifyMessage()
                + "stop time : " + currentTime);
        paused.set(false);
        running.set(false);

        // we must wait until workingThread is working
        class RepeatWaitThread implements Runnable {
            private Thread workingThread;

            RepeatWaitThread(Thread workingThread) {
                this.workingThread = workingThread;
            }

            @Override
            public void run() {
                try {
                    workingThread.join();
                } catch (InterruptedException e) {
                    AppLog.error(experimentHistory.getLoggerName(), RepeatWaitThread.class.getCanonicalName(),
                            experimentHistory.getLogIdentifyMessage()
                                    + "Interrupt while waiting end of experiment ", e);
                } finally {
                    updateAllHistory();
                    saveAllHistory();
                    ExperimentHistoryService.closeExperimentHistory(experimentHistory);
                    standardExperimentStart();
                }
            }
        }
        new Thread(new RepeatWaitThread(workingThread)).start();
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
