/**
 * Special variables and methods for "price" experiment
 * repeat N times with different price
 */

package ua.george_nika.simulation.model.experiment.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.impl.BusEntityInfo;
import ua.george_nika.simulation.model.experiment.ExperimentFactory;
import ua.george_nika.simulation.model.experiment.abstr.AbstractExperiment;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.impl.*;
import ua.george_nika.simulation.service.experiment.ExperimentHistoryService;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class PriceExperiment extends AbstractExperiment {

    public static final String EXPERIMENT_TYPE = "price";

    private int minPrice;
    private int maxPrice;
    private int repeat;
    private boolean zeroVariation;

    @XmlTransient
    private int repeatLeft;

    @XmlTransient
    private int currentPrice;

    static {
        ExperimentFactory.registerClassInFactory(EXPERIMENT_TYPE, PriceExperiment.class.getCanonicalName());
    }

    @Override
    public void startExecution() {
        repeatLeft = repeat;
        standardExperimentStart();
    }

    protected void standardExperimentStart() {
        if (repeatLeft > 0) {
            repeatLeft--;
            // repeat-1 == quantity of interval
            currentPrice = maxPrice - repeatLeft * ((maxPrice - minPrice) / (repeat - 1));
            if (repeatLeft == (repeat - 1)) {
                currentPrice = minPrice;
            }
            if (repeatLeft == 0) {
                currentPrice = maxPrice;
            }
            setCurrentPriceInAllGenerators(currentPrice);
            setZeroVariationInAllGenerators(zeroVariation);
            super.startExecution();
        } else {
            working.set(false);
        }
    }

    protected void setZeroVariationInAllGenerators(boolean zeroVariation) {
        if (zeroVariation) {
            for (Generator loopGenerator : generatorList) {
                if (loopGenerator instanceof StationGenerator) {
                    for (HumanAppearInfo loopHAInfo : ((StationGenerator) loopGenerator).getHumanAppearInfoList()) {
                        loopHAInfo.setVariation(0);
                    }
                }
            }
        }
    }

    protected void setCurrentPriceInAllGenerators(int price) {
        for (Generator loopGenerator : generatorList) {
            if (loopGenerator instanceof RouteGenerator) {
                BusEntityInfo busEntityInfo
                        = ClassTypeUtil.getCheckedClass(loopGenerator.getEntityInfo(), BusEntityInfo.class);
                busEntityInfo.setPriceInCent(price);
            }
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

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public boolean isZeroVariation() {
        return zeroVariation;
    }

    public void setZeroVariation(boolean zeroVariation) {
        this.zeroVariation = zeroVariation;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }
}
