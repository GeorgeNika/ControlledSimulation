/**
 * springMVC form
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.form;

import ua.george_nika.simulation.model.generator.Generator;

@SuppressWarnings("unused")

public class GeneratorForm {

    private String generatorName;
    private int entityQuantity;
    private String entityType;
    private int idEntityInfo;

    public GeneratorForm() {
    }

    public GeneratorForm(Generator generator) {
        this.generatorName = generator.getGeneratorName();
        this.entityQuantity = generator.getEntityQuantity();
        this.entityType = generator.getEntityType();
        this.idEntityInfo = generator.getIdEntityInfo();
    }

    public void updateGenerator(Generator generator) {
        generator.setGeneratorName(getGeneratorName());
        generator.setEntityQuantity(getEntityQuantity());
        generator.setEntityType(getEntityType());
        generator.setIdEntityInfo(getIdEntityInfo());
    }

    public String getGeneratorName() {
        return generatorName;
    }

    public void setGeneratorName(String generatorName) {
        this.generatorName = generatorName;
    }

    public int getEntityQuantity() {
        return entityQuantity;
    }

    public void setEntityQuantity(int entityQuantity) {
        this.entityQuantity = entityQuantity;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public int getIdEntityInfo() {
        return idEntityInfo;
    }

    public void setIdEntityInfo(int idEntityInfo) {
        this.idEntityInfo = idEntityInfo;
    }
}
