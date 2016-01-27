/**
 * Special variables and methods for "economy human" entity
 */

package ua.george_nika.simulation.model.entity.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.EntityFactory;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.util.ClassTypeUtil;

@Component
public class EconomyHumanEntity extends HumanEntity{

    public static final String ENTITY_TYPE = "economy_human";
    protected static String entityInfoType = EconomyHumanEntityInfo.ENTITY_INFO_TYPE;

    protected int maxPriceInCent;

    static {
        EntityFactory.registerClassInFactory(ENTITY_TYPE, EconomyHumanEntity.class.getCanonicalName());
    }

    public String getEntityType() {
        return ENTITY_TYPE;
    }

    public String getEntityInfoType() {
        return entityInfoType;
    }

    @Override
    public void setEntityInfo(EntityInfo entityInfo) {
        super.setEntityInfo(entityInfo);
        EconomyHumanEntityInfo economyHumanEntityInfo
                = ClassTypeUtil.getCheckedClass(entityInfo, EconomyHumanEntityInfo.class);
        this.maxPriceInCent = economyHumanEntityInfo.getMaxPriceInCent();
    }

    @Override
    public int getReasonablePriceInCent() {
        double waitInterval = currentTime.getMillis() - creationTime.getMillis();
        double priceInterval = (maxPriceInCent-reasonablePriceInCent);
        double percent = waitInterval/delayTimeToRemoveMs;
        return (int)(percent*priceInterval + reasonablePriceInCent);
    }
}
