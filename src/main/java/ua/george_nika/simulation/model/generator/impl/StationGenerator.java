package ua.george_nika.simulation.model.generator.impl;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.MutableDateTime;
import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.EntityFactory;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.generator.*;
import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.generator.abstr.AbstractGenerator;
import ua.george_nika.simulation.model.generator.error.EmptyHumanAppearInfoException;
import ua.george_nika.simulation.model.generator.error.NoNextHumanAppearInfoException;
import ua.george_nika.simulation.util.AppLog;

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
    }

    public void executeDependentAction(MutableDateTime newCurrentTime) {
        for (Entity loopEntity : dependentEntityList) {
            loopEntity.executeMainAction(newCurrentTime);
        }

        //clearingEntityList
        Iterator<Entity> tempIterator = dependentEntityList.iterator();
        Entity tempEntity;
        while (tempIterator.hasNext()) {
            tempEntity = tempIterator.next();
            if (tempEntity.isNeedRemove()) {
                tempIterator.remove();
            }
        }
        // todo log about remove
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
                result += getQuantityOfNewEntityForInterval(tempHumanAppearInfo, tempInterval);
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

    protected int getQuantityOfNewEntityForInterval(HumanAppearInfo intervalInfo, Interval newInterval) {
        int result;
        long fullIntervalDuration = intervalInfo.getEndTimeMs() - intervalInfo.getStartTimeMs();
        long smallIntervalDuration = newInterval.toDurationMillis();

        double percentOfCurrentPeriod = ((double) smallIntervalDuration) / fullIntervalDuration * 100;
        double quantityEntityForCurrentInterval = intervalInfo.getPercent() * entityQuantity / 100;
        result = (int) Math.round(quantityEntityForCurrentInterval * percentOfCurrentPeriod / 100);
        return result;
    }

    public List<HumanAppearInfo> getHumanAppearInfoList() {
        return humanAppearInfoList;
    }

    public void setHumanAppearInfoList(List<HumanAppearInfo> humanAppearInfoList) {
        this.humanAppearInfoList = humanAppearInfoList;
    }
}