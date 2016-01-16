package ua.george_nika.simulation.controller.light_ajax_info;

import ua.george_nika.simulation.model.generator.GeneratorHistory;

/**
 * Created by george on 12.01.2016.
 */
public class LightEntityRunInfo {
// todo
    private int idGeneratorHistory;
    private int idGenerator;
    private String generatorName;
    private String generatorType;
    private String infoString;

    public LightEntityRunInfo(GeneratorHistory generatorHistory) {
        this.idGeneratorHistory = generatorHistory.getIdGenerator();
        this.idGenerator = generatorHistory.getIdGenerator();
        this.generatorName = generatorHistory.getGeneratorName();
        this.generatorType = generatorHistory.getGeneratorType();
        this.infoString = generatorHistory.getRunInfoString();
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

    public String getGeneratorName() {
        return generatorName;
    }

    public void setGeneratorName(String generatorName) {
        this.generatorName = generatorName;
    }

    public String getGeneratorType() {
        return generatorType;
    }

    public void setGeneratorType(String generatorType) {
        this.generatorType = generatorType;
    }

    public String getInfoString() {
        return infoString;
    }

    public void setInfoString(String infoString) {
        this.infoString = infoString;
    }
}