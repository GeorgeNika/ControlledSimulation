package ua.george_nika.simulation.model.entity;

import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.GeneratorHistory;

/**
 * Created by george on 10.01.2016.
 */
public interface EntityHistory {

    void setInitialEntityHistoryData(Generator generator, Entity entity);

    void updateEntityHistory(Entity entity);

    String getRunInfoString();

    String getLogIdentifyMessage();



    public int getIdEntityHistory();

    public void setIdEntityHistory(int idEntityHistory);

    public int getIdEntity();

    public void setIdEntity(int idEntity);

    public int getIdGenerator();

    public void setIdGenerator(int idGenerator);

    public int getIdGeneratorHistory();

    public void setIdGeneratorHistory(int idGeneratorHistory);

    public String getEntityType();

    public void setEntityType(String entityType);

    public String getLoggerName();

    public void setLoggerName(String loggerName);
}
