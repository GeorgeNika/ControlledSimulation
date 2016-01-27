/**
 * Special variables and methods for "bus" entity
 */

package ua.george_nika.simulation.model.entity.impl;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.model.entity.abstr.AbstractEntity;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.RelatedGeneratorData;
import ua.george_nika.simulation.model.entity.*;
import ua.george_nika.simulation.model.generator.impl.BusStartInfo;
import ua.george_nika.simulation.util.AppConst;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.*;

@Component
public class BusEntity extends AbstractEntity {

    public static final String ENTITY_TYPE = "bus";
    protected static String entityInfoType = BusEntityInfo.ENTITY_INFO_TYPE;

    protected boolean currentForwardDirection;
    protected int repeatQuantity;
    protected boolean changeDirection;

    protected int capacity;
    protected int price;

    protected List<Entity> entityList = new LinkedList<>();
    protected Set<Generator> nextRelationGeneratorSet = new HashSet<>();

    static {
        EntityFactory.registerClassInFactory(ENTITY_TYPE, BusEntity.class.getCanonicalName());
    }

    public String getEntityType() {
        return ENTITY_TYPE;
    }

    public String getEntityInfoType() {
        return entityInfoType;
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
        this.idEntity = busEntityInfo.getIdEntityInfo();
        this.capacity = busEntityInfo.getCapacity();
        this.price = busEntityInfo.getPriceInCent();
    }


    public void executeMainAction(MutableDateTime newCurrentTime) {
        while (nextRelationTime.isBefore(newCurrentTime)) {
            Generator currentRelatedGenerator = relationGeneratorDataList.get(nextRelationPoint).getRelatedGenerator();
            logAboutComeToNextRelationGenerator(currentRelatedGenerator);
            executeRelation(currentRelatedGenerator);
            currentTime.setMillis(nextRelationTime);
            setNextRelationPoint();
        }
    }

    protected void logAboutComeToNextRelationGenerator(Generator currentRelatedGenerator) {
        AppLog.info(entityHistory.getLoggerName(), entityHistory.getLogIdentifyMessage()
                        + " come to " + currentRelatedGenerator.getGeneratorType()
                        + " generator - " + currentRelatedGenerator.getGeneratorName()
                        + " " + currentRelatedGenerator.getGeneratorHistory().getLogIdentifyMessage()
                        + " : current entity quantity - " + entityList.size()
        );
    }

    protected void executeRelation(Generator currentRelatedGenerator) {
        excludeGeneratorFromRelationGeneratorSet(currentRelatedGenerator);
        addNewGeneratorToRelationGeneratorSet();

        sendEntityToGenerator(currentRelatedGenerator);
        receiveEntityFromGenerator(currentRelatedGenerator);
    }

    protected void sendEntityToGenerator(Generator currentRelatedGenerator) {

        // create entity list for send and remove from this entity list
        List<Entity> sendEntityList = new ArrayList<>();
        Entity tempEntity;
        Iterator<Entity> tempIterator = entityList.iterator();
        while (tempIterator.hasNext()) {
            tempEntity = tempIterator.next();
            if (currentRelatedGenerator.equals(tempEntity.getNextRelationGeneratorData().getRelatedGenerator())) {
                sendEntityList.add(tempEntity);
                tempIterator.remove();
            }
        }

        // send entity to other generator
        currentRelatedGenerator.receiveEntityListFromEntity(sendEntityList, this);
        logAboutSendEntity(currentRelatedGenerator, sendEntityList.size());

    }

    protected void logAboutSendEntity(Generator currentRelatedGenerator, int quantity) {
        AppLog.info(entityHistory.getLoggerName(), entityHistory.getLogIdentifyMessage()
                        + " send to " + currentRelatedGenerator.getGeneratorType()
                        + " generator - " + currentRelatedGenerator.getGeneratorName()
                        + " " + currentRelatedGenerator.getGeneratorHistory().getLogIdentifyMessage()
                        + " : " + quantity + " entity "
        );
    }

    protected void receiveEntityFromGenerator(Generator currentRelatedGenerator) {
        // receive entity from other generator
        List<Entity> receiveList = currentRelatedGenerator.sendEntityListToEntity(this);
        entityList.addAll(receiveList);
        BusHistory busHistory = ClassTypeUtil.getCheckedClass(entityHistory, BusHistory.class);
        busHistory.addEntityIntoHistory(this, receiveList.size());
        logAboutReceiveEntity(currentRelatedGenerator, receiveList.size());
    }

    protected void logAboutReceiveEntity(Generator currentRelatedGenerator, int quantity) {
        AppLog.info(entityHistory.getLoggerName(), entityHistory.getLogIdentifyMessage()
                        + " receive from " + currentRelatedGenerator.getGeneratorType()
                        + " generator - " + currentRelatedGenerator.getGeneratorName()
                        + " " + currentRelatedGenerator.getGeneratorHistory().getLogIdentifyMessage()
                        + " : " + quantity + " entity "
        );
    }

    protected void initRelationGeneratorSet() {
        for (RelatedGeneratorData loopInfo : relationGeneratorDataList) {
            nextRelationGeneratorSet.add(loopInfo.getRelatedGenerator());
        }
        AppLog.info(entityHistory.getLoggerName(), entityHistory.getLogIdentifyMessage()
                        + " init relation generator set for " + getEntityType()
                        + " : count of relation - " + nextRelationGeneratorSet.size()
        );
    }

    protected void excludeGeneratorFromRelationGeneratorSet(Generator currentRelatedGenerator) {
        nextRelationGeneratorSet.remove(currentRelatedGenerator);
    }

    protected void addNewGeneratorToRelationGeneratorSet() {
        // do nothing
    }

    protected void decreaseRepeatQuantity() {
        repeatQuantity--;
        if (repeatQuantity <= 0) {
            needRemove = true;
        } else {
            initRelationGeneratorSet();
        }
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
        Generator nextGenerator = relationGeneratorDataList.get(nextRelationPoint).getRelatedGenerator();
        BusHistory busHistory = ClassTypeUtil.getCheckedClass(entityHistory, BusHistory.class);
        busHistory.setNextRelationPoint(nextGenerator, new MutableDateTime(nextRelationTime));
        logAboutSetNextRelationPoint(nextGenerator);
    }

    protected void logAboutSetNextRelationPoint(Generator generator) {
        AppLog.info(entityHistory.getLoggerName(), entityHistory.getLogIdentifyMessage()
                        + " next relation in " + nextRelationTime.toString(AppConst.DATE_TIME_FORMAT)
                        + " to " + generator.getGeneratorType()
                        + " generator - " + generator.getGeneratorName()
                        + " " + generator.getGeneratorHistory().getLogIdentifyMessage()
                        + " : current entity quantity - " + entityList.size()
        );
    }

    public Set<Generator> getNextRelationGeneratorSet() {
        return nextRelationGeneratorSet;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Entity> getEntityList() {
        return entityList;
    }

    public MutableDateTime getNextRelationTime() {
        return nextRelationTime;
    }
}
