/**
 * used for send request when get ajax response
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.light_ajax_info.generator;

import ua.george_nika.simulation.model.generator.RelatedGeneratorData;

@SuppressWarnings("unused")

public class LightRelationGeneratorData {

    protected int relatedPosition;
    protected String relatedGeneratorType;
    protected int idRelatedGenerator;
    protected String relatedGeneratorName;
    protected long delayMs;

    public LightRelationGeneratorData(RelatedGeneratorData relatedGeneratorData) {
        this.relatedPosition = relatedGeneratorData.getRelatedPosition();
        this.relatedGeneratorType = relatedGeneratorData.getRelatedGeneratorType();
        this.idRelatedGenerator = relatedGeneratorData.getIdRelatedGenerator();
        this.relatedGeneratorName = relatedGeneratorData.getRelatedGeneratorName();
        this.delayMs = relatedGeneratorData.getDelayMs();
    }

    public int getRelatedPosition() {
        return relatedPosition;
    }

    public void setRelatedPosition(int relatedPosition) {
        this.relatedPosition = relatedPosition;
    }

    public String getRelatedGeneratorType() {
        return relatedGeneratorType;
    }

    public void setRelatedGeneratorType(String relatedGeneratorType) {
        this.relatedGeneratorType = relatedGeneratorType;
    }

    public int getIdRelatedGenerator() {
        return idRelatedGenerator;
    }

    public void setIdRelatedGenerator(int idRelatedGenerator) {
        this.idRelatedGenerator = idRelatedGenerator;
    }

    public String getRelatedGeneratorName() {
        return relatedGeneratorName;
    }

    public void setRelatedGeneratorName(String relatedGeneratorName) {
        this.relatedGeneratorName = relatedGeneratorName;
    }

    public long getDelayMs() {
        return delayMs;
    }

    public void setDelayMs(long delayMs) {
        this.delayMs = delayMs;
    }
}
