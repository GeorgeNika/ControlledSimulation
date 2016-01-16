package ua.george_nika.simulation.model.entity.impl;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.model.entity.abstr.AbstractEntity;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.RelatedGeneratorData;
import ua.george_nika.simulation.model.entity.*;
import ua.george_nika.simulation.model.entity.error.WrongTypeInfo;
import ua.george_nika.simulation.model.generator.impl.BusStartInfo;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.*;

/**
 * Created by george on 23.11.2015.
 */

@Component
public class BusEntity extends AbstractEntity {

    public static final String ENTITY_TYPE = "bus";
    protected static String entityInfoType = BusEntityInfo.ENTITY_INFO_TYPE;

    protected boolean currentForwardDirection;
    protected int repeatQuantity;
    protected boolean changeDirection;

    protected int capacity;
    protected int price;


    protected List<Entity> entityList = new LinkedList<Entity>();
    protected Set<Generator> nextRelationGeneratorSet = new HashSet<Generator>();

    static {
        EntityFactory.registerClassInFactory(ENTITY_TYPE, BusEntity.class.getCanonicalName());
    }

    public String getEntityType() {
        return ENTITY_TYPE;
    }

    public String getEntityInfoType() {
        return entityInfoType;
    }

    public EntityInfo getEntityInfo() {
        return new BusEntityInfo(capacity, price);
    }

    public void initEntityAction(Generator generator, Object specialInfo, DateTime creationTime) {
        super.initEntityAction(generator, specialInfo, creationTime);
        setSpecialInfo(specialInfo);
        currentTime.setMillis(creationTime);
        nextRelationTime.setMillis(creationTime);
        if (currentForwardDirection) {
            nextRelationPoint = 0;
        } else {
            nextRelationPoint = relationGeneratorDataList.size() - 1;
        }
        initRelationGeneratorSet();
    }

    protected void setSpecialInfo(Object specialInfo) {
        BusStartInfo busStartInfo = ClassTypeUtil.getCheckedClass(specialInfo, BusStartInfo.class);
        this.currentForwardDirection = busStartInfo.isForwardDirection();
        this.repeatQuantity = busStartInfo.getRepeatQuantity();
        this.changeDirection = busStartInfo.isChangeDirection();
    }


    protected void setEntityInfo(EntityInfo entityInfo) {
        BusEntityInfo busEntityInfo = ClassTypeUtil.getCheckedClass(entityInfo, BusEntityInfo.class);
        this.capacity = busEntityInfo.getCapacity();
        this.price = busEntityInfo.getPriceInCent();
    }


    public void executeMainAction(MutableDateTime newCurrentTime) {
        while (nextRelationTime.isBefore(newCurrentTime)) {
            executeRelation();
            currentTime.setMillis(nextRelationTime);
            setNextRelationPoint();
        }
    }


    protected void executeRelation() {
        setNewRelationGeneratorSet();
        excludeEntityFromEntityList();
        getNewEntityToEntityList();
    }

    protected void excludeEntityFromEntityList() {
        Entity tempEntity;
        Iterator<Entity> tempIterator = entityList.iterator();
        Generator currentGenerator = relationGeneratorDataList.get(nextRelationPoint).getRelatedGenerator();
        while (tempIterator.hasNext()) {
            tempEntity = tempIterator.next();
            if (currentGenerator.equals(tempEntity.getNextRelationGeneratorInfo().getRelatedGenerator())) {
                tempIterator.remove();
            }
        }
    }


    protected void getNewEntityToEntityList() {
        Entity tempEntity;
        Generator tempGenerator = relationGeneratorDataList.get(nextRelationPoint).getRelatedGenerator();
        Iterator<Entity> tempIterator = tempGenerator.getDependentEntityList().iterator();

        while ((tempIterator.hasNext()) && (entityList.size() < capacity)) {
            tempEntity = tempIterator.next();
            if (successRelation(tempEntity)) {
                entityList.add(tempEntity);
                tempIterator.remove();
            }
        }
    }

    protected boolean successRelation(Entity relationEntity) {
        if (nextRelationGeneratorSet.contains(
                relationEntity.getNextRelationGeneratorInfo().getRelatedGenerator())) {
            EntityInfo tempEntityInfo = relationEntity.getEntityInfo();
            HumanEntityInfo humanEntityInfo = ClassTypeUtil.getCheckedClass(tempEntityInfo, HumanEntityInfo.class);
            if (humanEntityInfo.getReasonablePriceInCent() >= price) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    protected void setNewRelationGeneratorSet() {
        nextRelationGeneratorSet.remove(relationGeneratorDataList.get(nextRelationPoint).getRelatedGenerator());
    }

    protected void initRelationGeneratorSet() {
        for (RelatedGeneratorData loopInfo : relationGeneratorDataList) {
            nextRelationGeneratorSet.add(loopInfo.getRelatedGenerator());
        }
    }

    protected void decreaseRepeatQuantity() {
        repeatQuantity--;
        if (repeatQuantity <= 0) {
            needRemove = true;
        }
        initRelationGeneratorSet();
    }


    protected void setStartRelationPoint() {
        if (currentForwardDirection) {
            nextRelationPoint = 0;
        } else {
            nextRelationPoint = relationGeneratorDataList.size() - 1;
        }
    }

    protected void setNextRelationPoint() {
        if (currentForwardDirection) {
            nextRelationTime.add(relationGeneratorDataList.get(nextRelationPoint).getDelayMs());
            nextRelationPoint++;
        } else {
            if (nextRelationPoint > 0) {
                // if we go backward, when we have to take previous delay
                nextRelationTime.add(relationGeneratorDataList.get(nextRelationPoint - 1).getDelayMs());
            } else {
                // if we go backward and stay on first point,
                // when we have to take last delay witch mean rest between repeat moving across related generators
                nextRelationTime.add(relationGeneratorDataList.get(relationGeneratorDataList.size() - 1).getDelayMs());
            }
            nextRelationPoint--;
        }
        if ((nextRelationPoint < 0) || (nextRelationPoint >= relationGeneratorDataList.size())) {
            if (changeDirection) {
                currentForwardDirection = !currentForwardDirection;
            }
            setStartRelationPoint();
            decreaseRepeatQuantity();
        }
    }
}
