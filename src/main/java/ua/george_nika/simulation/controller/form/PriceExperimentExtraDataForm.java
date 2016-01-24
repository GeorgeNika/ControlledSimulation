/**
 * springMVC form
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.form;

import ua.george_nika.simulation.model.experiment.impl.PriceExperiment;

@SuppressWarnings("unused")

public class PriceExperimentExtraDataForm {

    private int minPrice;
    private int maxPrice;
    private int repeat;
    private boolean zeroVariation;

    public PriceExperimentExtraDataForm() {
    }

    public PriceExperimentExtraDataForm(PriceExperiment priceExperiment) {
        this.minPrice = priceExperiment.getMinPrice();
        this.maxPrice = priceExperiment.getMaxPrice();
        this.repeat = priceExperiment.getRepeat();
        this.zeroVariation = priceExperiment.isZeroVariation();

    }

    public void updateExtraDataExperiment(PriceExperiment priceExperiment) {
        priceExperiment.setMinPrice(minPrice);
        priceExperiment.setMaxPrice(maxPrice);
        priceExperiment.setRepeat(repeat);
        priceExperiment.setZeroVariation(zeroVariation);
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
}
