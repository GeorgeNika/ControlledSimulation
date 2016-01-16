package ua.george_nika.simulation.model.entity.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.model.entity.EntityInfoFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by george on 28.11.2015.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@Component
public class HumanEntityInfo implements EntityInfo {

    public static final String ENTITY_INFO_TYPE = "human_info";

    protected int idEntityInfo;
    protected int delayTimeToRemoveMs;
    protected int reasonablePriceInCent;


    static {
        EntityInfoFactory.registerClassInFactory(ENTITY_INFO_TYPE, HumanEntityInfo.class.getCanonicalName());
    }

    public HumanEntityInfo() {
    }

    public HumanEntityInfo(int delayTimeToRemoveMs, int reasonablePrice) {
        this.delayTimeToRemoveMs = delayTimeToRemoveMs;
        this.reasonablePriceInCent = reasonablePrice;
    }


    public String getEntityInfoType() {
        return ENTITY_INFO_TYPE;
    }

    public int getIdEntityInfo() {
        return idEntityInfo;
    }

    public void setIdEntityInfo(int idEntityInfo) {
        this.idEntityInfo = idEntityInfo;
    }

    @Override
    public String getInfoString() {
        return "HUMAN: id-" + idEntityInfo + ", wait(ms) -" + delayTimeToRemoveMs + ", price(cent) -" + reasonablePriceInCent;
    }

    public int getDelayTimeToRemoveMs() {
        return delayTimeToRemoveMs;
    }

    public void setDelayTimeToRemoveMs(int delayTimeToRemoveMs) {
        this.delayTimeToRemoveMs = delayTimeToRemoveMs;
    }

    public int getReasonablePriceInCent() {
        return reasonablePriceInCent;
    }

    public void setReasonablePriceInCent(int reasonablePriceInCent) {
        this.reasonablePriceInCent = reasonablePriceInCent;
    }
}
