/**
 * Special variables and methods for "bus start" info for "route" generator
 */

package ua.george_nika.simulation.model.generator.impl;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class BusStartInfo {

    protected int idBusStartInfo;
    protected int IdGenerator;
    protected int startTimeMs;
    protected boolean forwardDirection;
    protected int repeatQuantity;
    protected boolean changeDirection;

    public BusStartInfo() {
    }

    public BusStartInfo(int startTimeMs, boolean forwardDirection, int repeatQuantity, boolean changeDirection) {
        this.startTimeMs = startTimeMs;
        this.forwardDirection = forwardDirection;
        this.repeatQuantity = repeatQuantity;
        this.changeDirection = changeDirection;
    }

    public int getIdBusStartInfo() {
        return idBusStartInfo;
    }

    public void setIdBusStartInfo(int idBusStartInfo) {
        this.idBusStartInfo = idBusStartInfo;
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
