/**
 * Special variables and methods for "station" generator history
 */

package ua.george_nika.simulation.model.generator.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.GeneratorHistoryFactory;
import ua.george_nika.simulation.model.generator.abstr.AbstractGeneratorHistory;

@Component
public class StationGeneratorHistory extends AbstractGeneratorHistory {

    protected int sendEntity;
    protected int receiveEntity;

    protected int currentEntityCount;

    static {
        GeneratorHistoryFactory.registerClassInFactory(StationGenerator.GENERATOR_TYPE,
                StationGeneratorHistory.class.getCanonicalName());
    }

    @Override
    protected void updateGeneratorHistoryExtraData(Generator generator) {
        currentEntityCount = generator.getDependentEntityList().size();
    }

    public void sendEntity(int quantity) {
        sendEntity += quantity;
    }

    public void receiveEntity(int quantity) {
        receiveEntity += quantity;
    }

    @Override
    public String getRunInfoString() {
        return "current entity count - " + currentEntityCount;
    }

    @Override
    protected void setInitialGeneratorHistoryExtraData(Generator generator) {
        this.createEntity = 0;
        this.sendEntity = 0;
        this.receiveEntity = 0;
        this.destroyEntity = 0;

        this.currentEntityCount = 0;
    }


    public int getSendEntity() {
        return sendEntity;
    }

    public void setSendEntity(int sendEntity) {
        this.sendEntity = sendEntity;
    }

    public int getReceiveEntity() {
        return receiveEntity;
    }

    public void setReceiveEntity(int receiveEntity) {
        this.receiveEntity = receiveEntity;
    }
}
