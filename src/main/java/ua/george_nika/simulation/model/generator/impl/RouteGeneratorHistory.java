package ua.george_nika.simulation.model.generator.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.entity.impl.BusEntity;
import ua.george_nika.simulation.model.entity.impl.BusHistory;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.GeneratorHistoryFactory;
import ua.george_nika.simulation.model.generator.abstr.AbstractGeneratorHistory;
import ua.george_nika.simulation.util.ClassTypeUtil;

/**
 * Created by george on 11.01.2016.
 */
@Component
public class RouteGeneratorHistory extends AbstractGeneratorHistory {

    protected int createEntity;
    protected int processedEntity;
    protected int processedEntityFromDestroyedBus; // for save info when depend entity destroyed


    protected int currentEntityCount;
    protected int currentDependEntityCount;

    static {
        GeneratorHistoryFactory.registerClassInFactory(RouteGenerator.GENERATOR_TYPE,
                RouteGeneratorHistory.class.getCanonicalName());
    }


    @Override
    protected void updateGeneratorHistoryExtraData(Generator generator) {
        currentEntityCount = generator.getDependentEntityList().size();
        int tempCurrentCount = 0;
        int tempAllCount = 0;
        for (Entity loopEntity : generator.getDependentEntityList()) {
            BusHistory busHistory = ClassTypeUtil.getCheckedClass(loopEntity.getEntityHistory(), BusHistory.class);
            tempCurrentCount += busHistory.getCurrentEntityCount();
            tempAllCount += busHistory.getProcessedEntity();
        }
        currentDependEntityCount = tempCurrentCount;
        processedEntity = tempAllCount + processedEntityFromDestroyedBus;
    }

    @Override
    public String getRunInfoString() {
        return "current entity count - " + currentEntityCount
                + " : dependent entity count - " + currentDependEntityCount;
    }

    @Override
    protected void setInitialGeneratorHistoryExtraData(Generator generator) {
        this.createEntity = 0;
        this.processedEntity = 0;
        this.processedEntityFromDestroyedBus = 0;

        this.currentEntityCount = 0;
        this.currentDependEntityCount = 0;
    }


    public int getCreateEntity() {
        return createEntity;
    }

    public void setCreateEntity(int createEntity) {
        this.createEntity = createEntity;
    }

    public int getProcessedEntity() {
        return processedEntity;
    }

    public void setProcessedEntity(int processedEntity) {
        this.processedEntity = processedEntity;
    }

    public int getProcessedEntityFromDestroyedBus() {
        return processedEntityFromDestroyedBus;
    }

    public void setProcessedEntityFromDestroyedBus(int processedEntityFromDestroyedBus) {
        this.processedEntityFromDestroyedBus = processedEntityFromDestroyedBus;
    }
}
