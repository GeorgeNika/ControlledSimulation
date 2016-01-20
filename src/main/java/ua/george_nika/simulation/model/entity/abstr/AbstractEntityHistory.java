package ua.george_nika.simulation.model.entity.abstr;

import ua.george_nika.simulation.model.entity.Entity;
import ua.george_nika.simulation.model.entity.EntityHistory;
import ua.george_nika.simulation.model.generator.Generator;

/**
 * Created by george on 11.01.2016.
 */
abstract public class AbstractEntityHistory implements EntityHistory {

    private int idEntityHistory;
    private int idEntity;
    private int idGenerator;
    private int idGeneratorHistory;
    private String entityType;
    protected String loggerName;


    abstract protected void setInitialEntityHistoryExtraData(Generator generator, Entity entity);
    abstract protected void updateEntityHistoryExtraData(Entity entity);


    public void setInitialEntityHistoryData(Generator generator, Entity entity){
        this.idEntity = entity.getIdEntity();
        this.idGenerator = generator.getIdGenerator();
        this.idGeneratorHistory = generator.getGeneratorHistory().getIdGeneratorHistory();
        this.entityType = entity.getEntityType();
        this.loggerName = generator.getGeneratorHistory().getLoggerName();

        setInitialEntityHistoryExtraData( generator,  entity);
    }

    public void updateEntityHistory(Entity entity){

        updateEntityHistoryExtraData(entity);
    }

    @Override
    public String getLogIdentifyMessage() {
        return "[ent hist : "+idEntityHistory+" ] - ";
    }


    public int getIdEntityHistory() {
        return idEntityHistory;
    }

    public void setIdEntityHistory(int idEntityHistory) {
        this.idEntityHistory = idEntityHistory;
    }

    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
    }

    public int getIdGenerator() {
        return idGenerator;
    }

    public void setIdGenerator(int idGenerator) {
        this.idGenerator = idGenerator;
    }

    public int getIdGeneratorHistory() {
        return idGeneratorHistory;
    }

    public void setIdGeneratorHistory(int idGeneratorHistory) {
        this.idGeneratorHistory = idGeneratorHistory;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }
}
