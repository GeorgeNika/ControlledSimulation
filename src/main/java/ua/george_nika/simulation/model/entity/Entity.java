package ua.george_nika.simulation.model.entity;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.RelatedGeneratorData;

import java.util.List;

/**
 * Created by george on 23.11.2015.
 */
public interface Entity {


    void initEntityAction(Generator generator, Object specialInfo, DateTime creationTime);

    void saveEntityHistory();

    void updateEntityHistory();

    EntityInfo getEntityInfo();

    void executeMainAction(MutableDateTime newCurrentTime);

    void setNewCurrentTime(MutableDateTime newCurrentTime);

    boolean isNeedRemove();

    String getEntityType();

    String getEntityInfoType();

    RelatedGeneratorData getNextRelationGeneratorData();


    public EntityHistory getEntityHistory();

    public void setEntityHistory(EntityHistory entityHistory);

    public int getIdEntity();

    public void setIdEntity(int idEntity);

}
