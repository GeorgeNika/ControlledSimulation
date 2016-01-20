package ua.george_nika.simulation.model.generator.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.GeneratorHistoryFactory;
import ua.george_nika.simulation.model.generator.abstr.AbstractGeneratorHistory;

/**
 * Created by george on 11.01.2016.
 */
@Component
public class StationGeneratorHistory extends AbstractGeneratorHistory {

    protected int createEntity;
    protected int sendEntity;
    protected int receiveEntity;
    protected int destroyEntity;

    protected int currentEntityCount;


    static {
        GeneratorHistoryFactory.registerClassInFactory(StationGenerator.GENERATOR_TYPE,
                StationGeneratorHistory.class.getCanonicalName());
    }



    @Override
    protected void updateGeneratorHistoryExtraData(Generator generator) {
        currentEntityCount = generator.getDependentEntityList().size();
    }

    @Override
    public String getRunInfoString() {
        return "current entity count - "+currentEntityCount;
    }

    @Override
    protected void setInitialGeneratorHistoryExtraData(Generator generator) {
        this.createEntity = 0;
        this.sendEntity = 10;
        this.receiveEntity = 10;
        this.destroyEntity = 0;

        this.currentEntityCount=0;
    }

    public int getCreateEntity() {
        return createEntity;
    }

    public void setCreateEntity(int createEntity) {
        this.createEntity = createEntity;
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

    public int getDestroyEntity() {
        return destroyEntity;
    }

    public void setDestroyEntity(int destroyEntity) {
        this.destroyEntity = destroyEntity;
    }

    public int getCurrentEntityCount() {
        return currentEntityCount;
    }

    public void setCurrentEntityCount(int currentEntityCount) {
        this.currentEntityCount = currentEntityCount;
    }
}
