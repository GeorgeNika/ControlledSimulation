/**
 * used for send request when get ajax response
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.light_ajax_info.generator;

import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.util.TimeToStringUtil;

@SuppressWarnings("unused")

public class LightGenerator {

    private int idGenerator;
    private String generatorName;
    private String generatorType;
    private String entityType;
    private int idEntity;
    private int entityQuantity;
    private String created;
    private String updated;

    public LightGenerator(Generator generator) {
        this.idGenerator = generator.getIdGenerator();
        this.generatorName = generator.getGeneratorName();
        this.generatorType = generator.getGeneratorType();
        this.entityType = generator.getEntityType();
        this.idEntity = generator.getIdEntityInfo();
        this.entityQuantity = generator.getEntityQuantity();
        this.created = TimeToStringUtil.getSafeString(generator.getCreated());
        this.updated = TimeToStringUtil.getSafeString(generator.getUpdated());
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

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
    }

    public int getEntityQuantity() {
        return entityQuantity;
    }

    public void setEntityQuantity(int entityQuantity) {
        this.entityQuantity = entityQuantity;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
