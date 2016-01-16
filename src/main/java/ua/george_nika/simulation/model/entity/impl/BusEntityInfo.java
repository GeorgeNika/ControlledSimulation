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
public class BusEntityInfo implements EntityInfo {

    public static final String ENTITY_INFO_TYPE = "bus_info";

    protected int idEntityInfo;
    protected int capacity;
    protected int priceInCent;


    static {
        EntityInfoFactory.registerClassInFactory(ENTITY_INFO_TYPE, BusEntityInfo.class.getCanonicalName());
    }


    public BusEntityInfo() {
    }

    public BusEntityInfo(int capacity, int priceInCent) {
        this.capacity = capacity;
        this.priceInCent = priceInCent;
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
        return "BUS: id-" + idEntityInfo + ", capacity-" + capacity + ", price(cent) -" + priceInCent;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPriceInCent() {
        return priceInCent;
    }

    public void setPriceInCent(int priceInCent) {
        this.priceInCent = priceInCent;
    }
}
