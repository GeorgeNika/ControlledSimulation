/**
 * Special variables and methods for "route" generator
 */

package ua.george_nika.simulation.model.generator.impl;

import org.joda.time.MutableDateTime;
import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.EntityFactory;
import ua.george_nika.simulation.model.entity.impl.BusHistory;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.generator.*;
import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.generator.abstr.AbstractGenerator;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class RouteGenerator extends AbstractGenerator {

    public static final String GENERATOR_TYPE = "route";

    @XmlElementWrapper
    protected List<BusStartInfo> busStartInfoList = new ArrayList<>();

    static {
        GeneratorFactory.registerClassInFactory(GENERATOR_TYPE, RouteGenerator.class.getCanonicalName());
    }

    public String getGeneratorType() {
        return GENERATOR_TYPE;
    }

    @Override
    public void initAction(Experiment experiment) {
        super.initAction(experiment);
        // set real generator in RelatedGeneratorData
        for (RelatedGeneratorData loopInfo : relatedGeneratorDataList) {
            Generator tempGenerator = getGeneratorById(experiment.getGeneratorList(), loopInfo.getIdRelatedGenerator());
            loopInfo.setRelatedGenerator(tempGenerator);
            AppLog.info(generatorHistory.getLoggerName(), generatorHistory.getLogIdentifyMessage()
                            + "set relation with generator " + tempGenerator.getGeneratorType() + " : "
                            + tempGenerator.getIdGenerator() + " - " + tempGenerator.getGeneratorName()
            );
        }
    }

    public void executeMainAction(MutableDateTime newCurrentTime) {
        for (BusStartInfo loopInfo : busStartInfoList) {
            long busStartTimeMs = experimentStartTime.getMillis() + loopInfo.getStartTimeMs();
            if ((newCurrentTime.getMillis() > busStartTimeMs) && (currentTime.getMillis() <= busStartTimeMs)) {
                AppLog.info(generatorHistory.getLoggerName(), generatorHistory.getLogIdentifyMessage()
                                + " Create new entity " + getEntityType() + ". id - " + loopInfo.getIdBusStartInfo()
                                + " : start time(ms) - " + loopInfo.getStartTimeMs()
                                + " : repeat quantity - " + loopInfo.getRepeatQuantity()
                                + " : forward " + loopInfo.isForwardDirection()
                                + " : change direction  " + loopInfo.isChangeDirection()
                );
                Entity tempEntity = EntityFactory.getEntityByType(getEntityType());
                tempEntity.initEntityAction(this, loopInfo, experimentStartTime.plus(loopInfo.getStartTimeMs()));
                dependentEntityList.add(tempEntity);
                generatorHistory.addEntity(1);
            }
        }
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
        while (tempIterator.hasNext()) {
            tempEntity = tempIterator.next();
            if (tempEntity.isNeedRemove()) {
                generatorHistory.destroyEntity(1);
                BusHistory busHistory = ClassTypeUtil.getCheckedClass(tempEntity.getEntityHistory(), BusHistory.class);
                RouteGeneratorHistory routeGeneratorHistory =
                        ClassTypeUtil.getCheckedClass(generatorHistory, RouteGeneratorHistory.class);
                routeGeneratorHistory.addHistoryAboutDestroyedEntity(tempEntity);
                tempEntity.updateEntityHistory();
                tempEntity.saveEntityHistory();
                tempIterator.remove();
                AppLog.info(generatorHistory.getLoggerName(), generatorHistory.getLogIdentifyMessage()
                        + "destroy " + getEntityType() + " - " + busHistory.getIdEntityHistory());
            }
        }
    }

    public List<BusStartInfo> getBusStartInfoList() {
        return busStartInfoList;
    }

    public void setBusStartInfoList(List<BusStartInfo> busStartInfoList) {
        this.busStartInfoList = busStartInfoList;
    }


    @Override
    public List<Entity> sendEntityListToEntity(Entity recipientEntity) {
        // send empty list
        return new ArrayList<>();
    }

    @Override
    public void receiveEntityListFromEntity(List<Entity> entityList, Entity donorEntity) {
        // do nothing
    }
}
