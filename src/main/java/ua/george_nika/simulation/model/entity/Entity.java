/**
 * Interface for model object - entity
 */

package ua.george_nika.simulation.model.entity;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.RelatedGeneratorData;

@SuppressWarnings("unused")

public interface Entity {


    void initEntityAction(Generator generator, Object specialInfo, DateTime creationTime);

    void saveEntityHistory();

    void updateEntityHistory();

    void executeMainAction(MutableDateTime newCurrentTime);

    void setNewCurrentTime(MutableDateTime newCurrentTime);

    boolean isNeedRemove();

    String getEntityType();

    String getEntityInfoType();

    RelatedGeneratorData getNextRelationGeneratorData();


    // getter and setter from abstract class

    EntityHistory getEntityHistory();

    void setEntityHistory(EntityHistory entityHistory);

    int getIdEntity();

    void setIdEntity(int idEntity);

    DateTime getCreationTime();

    void setCreationTime(DateTime creationTime);

}
