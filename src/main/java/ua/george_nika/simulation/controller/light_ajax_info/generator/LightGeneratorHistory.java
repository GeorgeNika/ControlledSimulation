/**
 * used for send request when get ajax response
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.light_ajax_info.generator;

import ua.george_nika.simulation.model.generator.GeneratorHistory;

public class LightGeneratorHistory {

    protected int idGeneratorHistory;
    protected int idGenerator;
    protected int idExperimentHistory;
    protected int idExperiment;
    protected String generatorType;
    protected String generatorName;
    protected int createEntity;
    protected int destroyEntity;

    public LightGeneratorHistory(GeneratorHistory generatorHistory) {
        this.idGeneratorHistory = generatorHistory.getIdGeneratorHistory();
        this.idGenerator = generatorHistory.getIdGenerator();
        this.idExperimentHistory = generatorHistory.getIdExperimentHistory();
        this.idExperiment = generatorHistory.getIdExperiment();
        this.generatorType = generatorHistory.getGeneratorType();
        this.generatorName = generatorHistory.getGeneratorName();
        this.createEntity = generatorHistory.getCreateEntity();
        this.destroyEntity = generatorHistory.getDestroyEntity();
    }

    public int getIdGeneratorHistory() {
        return idGeneratorHistory;
    }

    public void setIdGeneratorHistory(int idGeneratorHistory) {
        this.idGeneratorHistory = idGeneratorHistory;
    }

    public int getIdGenerator() {
        return idGenerator;
    }

    public void setIdGenerator(int idGenerator) {
        this.idGenerator = idGenerator;
    }

    public int getIdExperimentHistory() {
        return idExperimentHistory;
    }

    public void setIdExperimentHistory(int idExperimentHistory) {
        this.idExperimentHistory = idExperimentHistory;
    }

    public int getIdExperiment() {
        return idExperiment;
    }

    public void setIdExperiment(int idExperiment) {
        this.idExperiment = idExperiment;
    }

    public String getGeneratorType() {
        return generatorType;
    }

    public void setGeneratorType(String generatorType) {
        this.generatorType = generatorType;
    }

    public String getGeneratorName() {
        return generatorName;
    }

    public void setGeneratorName(String generatorName) {
        this.generatorName = generatorName;
    }

    public int getCreateEntity() {
        return createEntity;
    }

    public void setCreateEntity(int createEntity) {
        this.createEntity = createEntity;
    }

    public int getDestroyEntity() {
        return destroyEntity;
    }

    public void setDestroyEntity(int destroyEntity) {
        this.destroyEntity = destroyEntity;
    }
}
