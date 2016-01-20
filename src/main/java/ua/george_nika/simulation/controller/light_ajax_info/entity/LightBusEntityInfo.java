/**
 * used for send request when get ajax response
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.light_ajax_info.entity;

import ua.george_nika.simulation.model.entity.impl.BusEntityInfo;

@SuppressWarnings("unused")

public class LightBusEntityInfo {

    protected int idEntityInfo;
    protected int capacity;
    protected int priceInCent;

    public LightBusEntityInfo(BusEntityInfo busEntityInfo) {
        this.idEntityInfo = busEntityInfo.getIdEntityInfo();
        this.capacity = busEntityInfo.getCapacity();
        this.priceInCent = busEntityInfo.getPriceInCent();
    }

    public int getIdEntityInfo() {
        return idEntityInfo;
    }

    public void setIdEntityInfo(int idEntityInfo) {
        this.idEntityInfo = idEntityInfo;
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



