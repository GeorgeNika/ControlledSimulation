package ua.george_nika.simulation.model.entity.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.EntityFactory;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.RelatedGeneratorData;
import ua.george_nika.simulation.util.AppLog;

/**
 * Created by george on 29.11.2015.
 */

@Component

public class BusRoundEntity extends BusEntity {

    public static final String ENTITY_TYPE = "round_bus";
    protected static String entityInfoType = BusEntityInfo.ENTITY_INFO_TYPE;

    protected long roundDurationInMills;

    static {
        EntityFactory.registerClassInFactory(ENTITY_TYPE, BusRoundEntity.class.getCanonicalName());
    }

    public String getEntityType() {
        return ENTITY_TYPE;
    }

    public String getEntityInfoType() {
        return entityInfoType;
    }


    @Override
    protected void initRelationGeneratorSet() {
        changeDirection = false;
        long tempMills = 0;
        for (RelatedGeneratorData loopInfo : relationGeneratorDataList) {
            tempMills += loopInfo.getDelayMs();
        }
        roundDurationInMills = tempMills / 2 + 1;   // slightly more then half
        addNewGeneratorToRelationGeneratorSet();
        AppLog.info(entityHistory.getLoggerName(), entityHistory.getLogIdentifyMessage()
                        + " init relation generator set for " + getEntityType()
                        + " : count of relation - " + nextRelationGeneratorSet.size()
        );
    }

    @Override
    protected void addNewGeneratorToRelationGeneratorSet() {

        // add new point if they near in this direction
        int addQuantity = 0;
        for (RelatedGeneratorData loopInfo : relationGeneratorDataList) {
            if (getMillsToRelationGeneratorInfo(loopInfo) <= roundDurationInMills) {
                if (loopInfo.getRelatedGenerator().equals(
                        relationGeneratorDataList.get(nextRelationPoint).getRelatedGenerator())) {
                    continue;
                }
                nextRelationGeneratorSet.add(loopInfo.getRelatedGenerator());
                addQuantity++;
            }
        }
        AppLog.info(entityHistory.getLoggerName(), entityHistory.getLogIdentifyMessage()
                + " add to relation set " + addQuantity + " generator(s)");
    }

    protected long getMillsToRelationGeneratorInfo(RelatedGeneratorData info) {
        long result = 0;
        long tempResult;
        int tempPoint = nextRelationPoint;
        Generator tempGenerator = relationGeneratorDataList.get(nextRelationPoint).getRelatedGenerator();
        while (!tempGenerator.equals(info.getRelatedGenerator())) {
            if (currentForwardDirection) {
                if (tempPoint >= relationGeneratorDataList.size() - 1) {
                    if (repeatQuantity > 1) {
                        tempResult = relationGeneratorDataList.get(tempPoint).getDelayMs();
                        tempPoint = 0;
                    } else {
                        return Long.MAX_VALUE;
                    }
                } else {
                    tempResult = relationGeneratorDataList.get(tempPoint).getDelayMs();
                    tempPoint++;
                }
            } else {
                if (tempPoint <= 0) {
                    if (repeatQuantity > 1) {
                        tempResult = relationGeneratorDataList.get(relationGeneratorDataList.size() - 1).getDelayMs();
                        tempPoint = relationGeneratorDataList.size() - 1;
                    } else {
                        return Long.MAX_VALUE;
                    }
                } else {
                    tempResult = relationGeneratorDataList.get(tempPoint - 1).getDelayMs();
                    tempPoint--;
                }
            }
            tempGenerator = relationGeneratorDataList.get(tempPoint).getRelatedGenerator();
            result = result + tempResult;
        }

        return result;

    }

    @Override
    protected void decreaseRepeatQuantity() {
        repeatQuantity--;
        if (repeatQuantity <= 0) {
            needRemove = true;
        }
    }
}
