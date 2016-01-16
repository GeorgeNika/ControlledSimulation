package ua.george_nika.simulation.controller.light_ajax_info;

import ua.george_nika.simulation.model.generator.impl.BusStartInfo;

/**
 * Created by george on 18.12.2015.
 */
public class LightBusStartInfo {

    protected int idBusStartInfo;
    protected int startTimeMs;
    protected boolean forwardDirection;
    protected int repeatQuantity;
    protected boolean changeDirection;

    public LightBusStartInfo(BusStartInfo busStartInfo) {
        this.idBusStartInfo = busStartInfo.getIdBusStartInfo();
        this.startTimeMs = busStartInfo.getStartTimeMs();
        this.forwardDirection = busStartInfo.isForwardDirection();
        this.repeatQuantity = busStartInfo.getRepeatQuantity();
        this.changeDirection = busStartInfo.isChangeDirection();
    }


    public int getIdBusStartInfo() {
        return idBusStartInfo;
    }

    public void setIdBusStartInfo(int idBusStartInfo) {
        this.idBusStartInfo = idBusStartInfo;
    }

    public int getStartTimeMs() {
        return startTimeMs;
    }

    public void setStartTimeMs(int startTimeMs) {
        this.startTimeMs = startTimeMs;
    }

    public boolean isForwardDirection() {
        return forwardDirection;
    }

    public void setForwardDirection(boolean forwardDirection) {
        this.forwardDirection = forwardDirection;
    }

    public int getRepeatQuantity() {
        return repeatQuantity;
    }

    public void setRepeatQuantity(int repeatQuantity) {
        this.repeatQuantity = repeatQuantity;
    }

    public boolean isChangeDirection() {
        return changeDirection;
    }

    public void setChangeDirection(boolean changeDirection) {
        this.changeDirection = changeDirection;
    }
}
