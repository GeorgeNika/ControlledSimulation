package ua.george_nika.simulation.model.generator;

import javax.xml.bind.annotation.*;

/**
 * Created by george on 26.11.2015.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class RelatedGeneratorData {

    protected int idRelatedGenerator;
    protected String relatedGeneratorType;
    protected String relatedGeneratorName;
    protected long delayMs;
    protected int relatedPosition;

    @XmlTransient
    protected Generator relatedGenerator;

    public RelatedGeneratorData(){
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

    public String getRelatedGeneratorType() {
        return relatedGeneratorType;
    }

    public void setRelatedGeneratorType(String relatedGeneratorType) {
        this.relatedGeneratorType = relatedGeneratorType;
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

    public int getRelatedPosition() {
        return relatedPosition;
    }

    public void setRelatedPosition(int relatedPosition) {
        this.relatedPosition = relatedPosition;
    }

    public Generator getRelatedGenerator() {
        return relatedGenerator;
    }

    public void setRelatedGenerator(Generator relatedGenerator) {
        this.relatedGenerator = relatedGenerator;
    }
}
