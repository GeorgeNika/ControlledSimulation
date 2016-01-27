/**
 * Special variables and methods for "price" experiment histroy
 */

package ua.george_nika.simulation.model.experiment.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentHistoryFactory;
import ua.george_nika.simulation.model.experiment.abstr.AbstractExperimentHistory;
import ua.george_nika.simulation.util.ClassTypeUtil;

@Component
public class PriceExperimentHistory extends AbstractExperimentHistory {

    public static final String EXPERIMENT_TYPE = PriceExperiment.EXPERIMENT_TYPE;

    private int price;

    static {
        ExperimentHistoryFactory.registerClassInFactory(EXPERIMENT_TYPE,
                PriceExperimentHistory.class.getCanonicalName());
    }

    @Override
    protected void setInitialExperimentHistoryExtraData(Experiment experiment) {
        PriceExperiment priceExperiment = ClassTypeUtil.getCheckedClass(experiment, PriceExperiment.class);
        this.price = priceExperiment.getCurrentPrice();
    }

    @Override
    protected void updateExperimentHistoryExtraData(Experiment experiment) {
        PriceExperiment priceExperiment = ClassTypeUtil.getCheckedClass(experiment, PriceExperiment.class);
        this.price = priceExperiment.getCurrentPrice();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
