package ua.george_nika.simulation.model.generator.impl;

import org.joda.time.MutableDateTime;
import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.EntityFactory;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.generator.*;
import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.generator.abstr.AbstractGenerator;
import ua.george_nika.simulation.util.AppLog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**

 *
 *
 */

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
                                + "create new " + getEntityType() + ". id - " + loopInfo.getIdBusStartInfo()
                                + " : start time(ms) - " + loopInfo.getStartTimeMs()
                                + " : repeat quantity - " + loopInfo.getRepeatQuantity()
                                + " : forward " + loopInfo.isForwardDirection()
                                + " : change direction  " + loopInfo.isChangeDirection()
                );
                Entity tempEntity = EntityFactory.getEntityByType(getEntityType());
                tempEntity.initEntityAction(this, loopInfo, experimentStartTime.plus(loopInfo.getStartTimeMs()));
                dependentEntityList.add(tempEntity);
            }
        }
    }

    public void executeDependentAction(MutableDateTime newCurrentTime) {
        for (Entity loopEntity : dependentEntityList) {
            loopEntity.executeMainAction(newCurrentTime);
        }
    }

    public List<BusStartInfo> getBusStartInfoList() {
        return busStartInfoList;
    }

    public void setBusStartInfoList(List<BusStartInfo> busStartInfoList) {
        this.busStartInfoList = busStartInfoList;
    }
}
