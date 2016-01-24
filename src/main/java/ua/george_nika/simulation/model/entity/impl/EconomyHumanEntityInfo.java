package ua.george_nika.simulation.model.entity.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.EntityInfoFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by george on 20.01.2016.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@Component
public class EconomyHumanEntityInfo extends HumanEntityInfo {

    public static final String ENTITY_INFO_TYPE = "economy_human_info";

    protected int maxPriceInCent;

    static {
        EntityInfoFactory.registerClassInFactory(ENTITY_INFO_TYPE, EconomyHumanEntityInfo.class.getCanonicalName());
    }

    public String getEntityInfoType() {
        return ENTITY_INFO_TYPE;
    }

    @Override
    public String getInfoString() {
        return "Economy Human: id-" + idEntityInfo + ", wait(ms) -" + delayTimeToRemoveMs
                + ", low price(cent) -" + reasonablePriceInCent + ", max price(cent) -" + maxPriceInCent;
    }

    public int getMaxPriceInCent() {
        return maxPriceInCent;
    }

    public void setMaxPriceInCent(int maxPriceInCent) {
        this.maxPriceInCent = maxPriceInCent;
    }
}
