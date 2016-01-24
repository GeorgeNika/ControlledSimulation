package ua.george_nika.simulation.model.generator.impl;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by george on 26.11.2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class HumanAppearInfo {

    protected int idHumanAppearInfo;
    protected int IdGenerator;
    protected int startTimeMs;
    protected int endTimeMs;
    protected double percent;
    protected double variation;

    @XmlTransient
    protected double currentVariationRatio;

    public HumanAppearInfo() {
    }


    public int getIdHumanAppearInfo() {
        return idHumanAppearInfo;
    }

    public void setIdHumanAppearInfo(int idHumanAppearInfo) {
        this.idHumanAppearInfo = idHumanAppearInfo;
    }

    public int getIdGenerator() {
        return IdGenerator;
    }

    public void setIdGenerator(int idGenerator) {
        IdGenerator = idGenerator;
    }

    public int getStartTimeMs() {
        return startTimeMs;
    }

    public void setStartTimeMs(int startTimeMs) {
        this.startTimeMs = startTimeMs;
    }

    public int getEndTimeMs() {
        return endTimeMs;
    }

    public void setEndTimeMs(int endTimeMs) {
        this.endTimeMs = endTimeMs;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public double getVariation() {
        return variation;
    }

    public void setVariation(double variation) {
        this.variation = variation;
    }

    public double getCurrentVariationRatio() {
        return currentVariationRatio;
    }

    public void setCurrentVariationRatio(double currentVariationRatio) {
        this.currentVariationRatio = currentVariationRatio;
    }
}
