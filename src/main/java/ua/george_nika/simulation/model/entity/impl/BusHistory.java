/**
 * Special variables and methods for "bus" entity history
 */

package ua.george_nika.simulation.model.entity.impl;

import org.joda.time.MutableDateTime;
import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.entity.EntityHistoryFactory;
import ua.george_nika.simulation.model.entity.abstr.AbstractEntityHistory;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.util.ClassTypeUtil;

@Component
public class BusHistory extends AbstractEntityHistory {

    protected int processedEntity;
    protected long totalAmount;

    protected int currentEntityCount;
    protected String nextRelationGeneratorName;
    protected MutableDateTime nextRelationGeneratorTime;

    static {
        EntityHistoryFactory.registerClassInFactory(BusEntity.ENTITY_TYPE, BusHistory.class.getCanonicalName());
    }

    @Override
    protected void setInitialEntityHistoryExtraData(Generator generator, Entity entity) {
        this.processedEntity = 0;
        this.currentEntityCount = 0;
        this.totalAmount = 0;
        this.nextRelationGeneratorName = "not set yet";
        BusEntity busEntity = ClassTypeUtil.getCheckedClass(entity, BusEntity.class);
        this.nextRelationGeneratorTime = new MutableDateTime(busEntity.getNextRelationTime());
    }

    @Override
    protected void updateEntityHistoryExtraData(Entity entity) {
        BusEntity busEntity = ClassTypeUtil.getCheckedClass(entity, BusEntity.class);
        currentEntityCount = busEntity.getEntityList().size();
    }

    public void addEntityIntoHistory(Entity entity, int quantity) {
        BusEntity busEntity = ClassTypeUtil.getCheckedClass(entity, BusEntity.class);
        processedEntity += quantity;
        totalAmount = processedEntity * busEntity.price;
    }

    public void setNextRelationPoint(Generator generator, MutableDateTime nextTime) {
        nextRelationGeneratorName = generator.getGeneratorName();
        nextRelationGeneratorTime = nextTime;
    }

    @Override
    public String getRunInfoString() {
        return "Next point - " + nextRelationGeneratorName + " in " + nextRelationGeneratorTime
                + ". Current entity count - " + currentEntityCount;
    }

    public int getProcessedEntity() {
        return processedEntity;
    }

    public void setProcessedEntity(int processedEntity) {
        this.processedEntity = processedEntity;
    }

    public int getCurrentEntityCount() {
        return currentEntityCount;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

}
