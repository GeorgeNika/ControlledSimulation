/**
 * Special variables and methods for "route" generator history
 */

package ua.george_nika.simulation.model.generator.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.entity.impl.BusEntity;
import ua.george_nika.simulation.model.entity.impl.BusHistory;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.GeneratorHistoryFactory;
import ua.george_nika.simulation.model.generator.abstr.AbstractGeneratorHistory;
import ua.george_nika.simulation.util.ClassTypeUtil;

@Component
public class RouteGeneratorHistory extends AbstractGeneratorHistory {

    protected int processedEntity;
    protected long totalAmount;
    protected int processedEntityFromDestroyedBus; // for save info when depend entity destroyed
    protected long totalAmountFromDestroyedBus; // for save info when depend entity destroyed

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
        long tempTotalAmount = 0;
        for (Entity loopEntity : generator.getDependentEntityList()) {
            BusHistory busHistory = ClassTypeUtil.getCheckedClass(loopEntity.getEntityHistory(), BusHistory.class);
            tempCurrentCount += busHistory.getCurrentEntityCount();
            tempAllCount += busHistory.getProcessedEntity();
            tempTotalAmount += busHistory.getTotalAmount();
        }
        currentDependEntityCount = tempCurrentCount;
        processedEntity = tempAllCount + processedEntityFromDestroyedBus;
        totalAmount = tempTotalAmount + totalAmountFromDestroyedBus;
    }

    @Override
    public String getRunInfoString() {
        return "current entity count - " + currentEntityCount
                + " : dependent entity count - " + currentDependEntityCount;
    }

    public void addHistoryAboutDestroyedEntity(Entity entity) {
        BusEntity busEntity = ClassTypeUtil.getCheckedClass(entity, BusEntity.class);
        BusHistory busHistory = ClassTypeUtil.getCheckedClass(entity.getEntityHistory(), BusHistory.class);
        processedEntityFromDestroyedBus += busHistory.getProcessedEntity();
        totalAmountFromDestroyedBus = processedEntityFromDestroyedBus * busEntity.getPrice();
    }

    @Override
    protected void setInitialGeneratorHistoryExtraData(Generator generator) {
        this.createEntity = 0;
        this.processedEntity = 0;
        this.totalAmount = 0;
        this.processedEntityFromDestroyedBus = 0;

        this.currentEntityCount = 0;
        this.currentDependEntityCount = 0;
    }


    public int getProcessedEntity() {
        return processedEntity;
    }

    public void setProcessedEntity(int processedEntity) {
        this.processedEntity = processedEntity;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }
}
