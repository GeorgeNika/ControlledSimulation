package ua.george_nika.simulation.model.generator.impl;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.MutableDateTime;
import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.EntityFactory;
import ua.george_nika.simulation.model.entity.impl.BusEntity;
import ua.george_nika.simulation.model.entity.impl.HumanEntity;
import ua.george_nika.simulation.model.entity.impl.HumanEntityInfo;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.generator.*;
import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.generator.abstr.AbstractGenerator;
import ua.george_nika.simulation.model.generator.error.EmptyHumanAppearInfoException;
import ua.george_nika.simulation.model.generator.error.NoNextHumanAppearInfoException;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * Created by george on 23.11.2015.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class StationGenerator extends AbstractGenerator {

    public static final String GENERATOR_TYPE = "station";
    @XmlElementWrapper
    protected List<HumanAppearInfo> humanAppearInfoList = new ArrayList<HumanAppearInfo>();

    static {
        GeneratorFactory.registerClassInFactory(GENERATOR_TYPE, StationGenerator.class.getCanonicalName());
    }

    public String getGeneratorType() {
        return GENERATOR_TYPE;
    }

    @Override
    public void initAction(Experiment experiment) {
        super.initAction(experiment);

        // fill current variation for human appear info
        for (HumanAppearInfo loopHAInfo : humanAppearInfoList) {
            // fix current variation between  -variation and +variation
            double currentVariation = loopHAInfo.getVariation() * (2 * Math.random() - 1);

            loopHAInfo.setCurrentVariationRatio((100 + currentVariation) / 100);
        }
    }

    public void afterInitAction(Experiment experiment) {
        super.afterInitAction(experiment);

        // fill relatedGeneratorDataList with all available generators
        for (Integer loopId : getIdSetWithRelatedGenerator(experiment)) {
            RelatedGeneratorData relatedGeneratorData = new RelatedGeneratorData();
            // new relatedGeneratorData must be with idRelatedGenerator == 0
            Generator tempGenerator = getGeneratorById(experiment.getGeneratorList(), loopId);
            relatedGeneratorData.setRelatedGenerator(tempGenerator);
            relatedGeneratorDataList.add(relatedGeneratorData);
            AppLog.info(generatorHistory.getLoggerName(), generatorHistory.getLogIdentifyMessage()
                    + "add relation with generator " + tempGenerator.getGeneratorType() + " : "
                    + tempGenerator.getIdGenerator() + " - " + tempGenerator.getGeneratorName());
        }
    }

    protected Set<Integer> getIdSetWithRelatedGenerator(Experiment experiment) {
        Set<Integer> resultIdSet = new HashSet<>();

        // fill set with all generators which available through RouteGenerator or other
        for (Generator loopGenerator : experiment.getGeneratorList()) {
            // if new relatedGeneratorData still have id == 0, when we should not check for RouteGenerator
            // like  -  if (loopGenerator instanceof RouteGenerator) {
            // otherwise we should check that current station don`t touch related generators from other station
            if (isGeneratorRelatedToThisGenerator(loopGenerator)) {
                addAllRelationGeneratorToSet(loopGenerator.getRelatedGeneratorDataList(), resultIdSet);
            }
        }

        // remove current generator from set
        resultIdSet.remove(this.getIdGenerator());

        return resultIdSet;
    }

    protected void addAllRelationGeneratorToSet(List<RelatedGeneratorData> relationList, Set<Integer> resultSet) {
        for (RelatedGeneratorData loopInfo : relationList) {
            resultSet.add(loopInfo.getIdRelatedGenerator());
        }
    }

    protected boolean isGeneratorRelatedToThisGenerator(Generator generator) {
        for (RelatedGeneratorData loopInfo : generator.getRelatedGeneratorDataList()) {
            if (loopInfo.getIdRelatedGenerator() == idGenerator) {
                return true;
            }
        }
        return false;
    }

    public void executeMainAction(MutableDateTime newCurrentTime) {
        int quantityNewEntity = getNewEntityQuantity(newCurrentTime);
        AppLog.info(generatorHistory.getLoggerName(), generatorHistory.getLogIdentifyMessage()
                + "create new " + getEntityType() + ". Quantity - " + quantityNewEntity);
        Entity tempEntity;
        for (int i = 0; i < quantityNewEntity; i++) {
            tempEntity = EntityFactory.getEntityByType(getEntityType());
            tempEntity.initEntityAction(this, null, currentTime.toDateTime());
            dependentEntityList.add(tempEntity);
        }
        generatorHistory.addEntity(quantityNewEntity);
    }

    public void executeDependentAction(MutableDateTime newCurrentTime) {
        for (Entity loopEntity : dependentEntityList) {
            loopEntity.executeMainAction(newCurrentTime);
        }
        clearEntityList();
    }

    protected void clearEntityList() {
        Iterator<Entity> tempIterator = dependentEntityList.iterator();
        Entity tempEntity;
        int tempDestroyCount = 0;
        while (tempIterator.hasNext()) {
            tempEntity = tempIterator.next();
            if (tempEntity.isNeedRemove()) {
                tempEntity.updateEntityHistory();
                tempEntity.saveEntityHistory();
                tempIterator.remove();
                tempDestroyCount++;
            }
        }
        if (tempDestroyCount > 0) {
            generatorHistory.destroyEntity(tempDestroyCount);
            AppLog.info(generatorHistory.getLoggerName(), generatorHistory.getLogIdentifyMessage()
                    + "destroy " + getEntityType() + ". Quantity - " + tempDestroyCount);
        }
    }

    protected int getNewEntityQuantity(MutableDateTime newCurrentTime) {
        int result = 0;
        MutableDateTime tempCurrentTime = new MutableDateTime(currentTime);
        Interval tempInterval;
        HumanAppearInfo tempHumanAppearInfo;
        while (tempCurrentTime.isBefore(newCurrentTime)) {
            try {
                tempHumanAppearInfo = getHumanAppearInfo(tempCurrentTime);
                tempInterval = new Interval(tempCurrentTime, getNextTime(tempHumanAppearInfo, newCurrentTime));
                result += getNewEntityQuantityForInterval(tempHumanAppearInfo, tempInterval);
                tempCurrentTime.add(tempInterval.toDuration());
            } catch (EmptyHumanAppearInfoException e) {
                try {
                    tempCurrentTime = getTimeStartNextInterval(tempCurrentTime);
                } catch (NoNextHumanAppearInfoException ex) {
                    tempCurrentTime = newCurrentTime;
                }
            }
        }
        return result;
    }

    protected HumanAppearInfo getHumanAppearInfo(MutableDateTime time) throws EmptyHumanAppearInfoException {
        for (HumanAppearInfo loopGenerator : humanAppearInfoList) {
            long startTimeMs = experimentStartTime.getMillis() + loopGenerator.getStartTimeMs();
            long endTimeMs = experimentStartTime.getMillis() + loopGenerator.getEndTimeMs();
            if ((time.getMillis() < endTimeMs) && (time.getMillis() > startTimeMs)) {
                return loopGenerator;
            }
        }
        throw new EmptyHumanAppearInfoException();
    }

    protected DateTime getNextTime(HumanAppearInfo humanAppearInfo, MutableDateTime newTime) {
        long endTimeMs = experimentStartTime.getMillis() + humanAppearInfo.getEndTimeMs();
        if (newTime.getMillis() > endTimeMs) {
            return new DateTime(endTimeMs);
        } else {
            return newTime.toDateTime();
        }
    }

    protected MutableDateTime getTimeStartNextInterval(MutableDateTime time) {
        MutableDateTime resultTime = new MutableDateTime(time);
        boolean exist = false;
        for (HumanAppearInfo loopGenerator : humanAppearInfoList) {
            long startTimeMs = experimentStartTime.getMillis() + loopGenerator.getStartTimeMs();
            if (time.getMillis() < startTimeMs) {
                if ((!exist) || (resultTime.getMillis() > startTimeMs)) {
                    resultTime.setMillis(startTimeMs);
                    exist = true;
                }
            }
        }
        if (exist) {
            return resultTime;
        } else {
            throw new NoNextHumanAppearInfoException();
        }
    }

    protected int getNewEntityQuantityForInterval(HumanAppearInfo intervalInfo, Interval newInterval) {
        double fullIntervalDuration = intervalInfo.getEndTimeMs() - intervalInfo.getStartTimeMs();
        double smallIntervalDuration = newInterval.toDurationMillis();
        double percentOfSmallInterval = (smallIntervalDuration * 100) / fullIntervalDuration;

        double entityQuantityForFullInterval = intervalInfo.getPercent() * entityQuantity
                * intervalInfo.getCurrentVariationRatio() / 100;

        int result = (int) Math.round(entityQuantityForFullInterval * percentOfSmallInterval / 100);
        return result;
    }

    public List<HumanAppearInfo> getHumanAppearInfoList() {
        return humanAppearInfoList;
    }

    public void setHumanAppearInfoList(List<HumanAppearInfo> humanAppearInfoList) {
        this.humanAppearInfoList = humanAppearInfoList;
    }

    @Override
    public List<Entity> sendEntityListToEntity(Entity recipientEntity) {

        List<Entity> resultSendEntityList = new ArrayList<>();
        BusEntity busEntity = ClassTypeUtil.getCheckedClass(recipientEntity, BusEntity.class);
        int sendEntityListMaxSize = busEntity.getCapacity() - busEntity.getEntityList().size();

        // check all entity in this list
        // and if it satisfy requirement then remove it from our list and send
        Entity checkedEntity;
        Iterator<Entity> tempIterator = this.dependentEntityList.iterator();
        while ((tempIterator.hasNext()) && (resultSendEntityList.size() < sendEntityListMaxSize)) {
            checkedEntity = tempIterator.next();
            if (successRelation(checkedEntity, busEntity)) {
                resultSendEntityList.add(checkedEntity);
                tempIterator.remove();
            }
        }

        StationGeneratorHistory stationGeneratorHistory =
                ClassTypeUtil.getCheckedClass(generatorHistory, StationGeneratorHistory.class);
        stationGeneratorHistory.sendEntity(resultSendEntityList.size());
        AppLog.info(generatorHistory.getLoggerName(), generatorHistory.getLogIdentifyMessage()
                + " send " + getEntityType() + " to " + recipientEntity.getEntityType()
                + " with " + recipientEntity.getEntityHistory().getLogIdentifyMessage()
                + ". Quantity - " + resultSendEntityList.size());

        return resultSendEntityList;
    }

    protected boolean successRelation(Entity checkedEntity, BusEntity busEntity) {
        Set<Generator> nextRelationGeneratorSet = busEntity.getNextRelationGeneratorSet();
        int busPrice = busEntity.getPrice();
        if (nextRelationGeneratorSet.contains(
                checkedEntity.getNextRelationGeneratorData().getRelatedGenerator())) {
            HumanEntity humanEntity = ClassTypeUtil.getCheckedClass(checkedEntity, HumanEntity.class);
            if (busPrice <= humanEntity.getReasonablePriceInCent()) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    @Override
    public void receiveEntityListFromEntity(List<Entity> entityList, Entity donorEntity) {
        StationGeneratorHistory stationGeneratorHistory =
                ClassTypeUtil.getCheckedClass(generatorHistory, StationGeneratorHistory.class);
        stationGeneratorHistory.receiveEntity(entityList.size());
        AppLog.info(generatorHistory.getLoggerName(), generatorHistory.getLogIdentifyMessage()
                + " receive " + getEntityType() + " from " + donorEntity.getEntityType()
                + " with " + donorEntity.getEntityHistory().getLogIdentifyMessage()
                + ". Quantity - " + entityList.size());
    }
}
