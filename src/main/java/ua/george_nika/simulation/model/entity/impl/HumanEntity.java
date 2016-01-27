/**
 * Special variables and methods for "human" entity
 */

package ua.george_nika.simulation.model.entity.impl;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.abstr.AbstractEntity;
import ua.george_nika.simulation.model.entity.EntityFactory;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.model.entity.error.StationWithoutRoute;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.util.ClassTypeUtil;

@Component
public class HumanEntity extends AbstractEntity {

    public static final String ENTITY_TYPE = "human";
    protected static String entityInfoType = HumanEntityInfo.ENTITY_INFO_TYPE;

    protected int delayTimeToRemoveMs;
    protected int reasonablePriceInCent;

    static {
        EntityFactory.registerClassInFactory(ENTITY_TYPE, HumanEntity.class.getCanonicalName());
    }

    public String getEntityType() {
        return ENTITY_TYPE;
    }

    public String getEntityInfoType() {
        return entityInfoType;
    }

    public void setEntityInfo(EntityInfo entityInfo) {
        HumanEntityInfo humanEntityInfo = ClassTypeUtil.getCheckedClass(entityInfo, HumanEntityInfo.class);
        this.idEntity = humanEntityInfo.getIdEntityInfo();
        this.delayTimeToRemoveMs = humanEntityInfo.getDelayTimeToRemoveMs();
        this.reasonablePriceInCent = humanEntityInfo.getReasonablePriceInCent();
    }

    public void initEntityAction(Generator generator, Object specialInfo, DateTime creationTime) {
        // we are not use super because we no need entityHistory
        // super.initEntityAction(generator, specialInfo, creationTime);

        this.creationTime = creationTime;
        this.relationGeneratorDataList = generator.getRelatedGeneratorDataList();
        setEntityInfo(generator.getEntityInfo());

        currentTime.setMillis(creationTime);
        nextRelationTime.setMillis(creationTime);
        if (relationGeneratorDataList.size() == 0) {
            throw new StationWithoutRoute();
        }
        nextRelationPoint = (int) Math.round(Math.random() * (relationGeneratorDataList.size() - 1));
    }

    public void executeMainAction(MutableDateTime newCurrentTime) {
        if (newCurrentTime.isAfter(creationTime.plus(delayTimeToRemoveMs))) {
            needRemove = true;
        }
    }

    @Override
    public void saveEntityHistory() {
        // do nothing because we no need entityHistory for human
    }

    @Override
    public void updateEntityHistory() {
        // do nothing because we no need entityHistory for human
    }

    public int getReasonablePriceInCent() {
        return reasonablePriceInCent;
    }
}
