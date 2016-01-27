/**
 * Special variables and methods for "simple" experiment
 * do our experiment 1 time
 */

package ua.george_nika.simulation.model.experiment.impl;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.experiment.abstr.AbstractExperiment;
import ua.george_nika.simulation.model.experiment.ExperimentFactory;
import ua.george_nika.simulation.util.DateTimeXmlAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class SimpleExperiment extends AbstractExperiment {

    public static final String EXPERIMENT_TYPE = "simple";

    private String infoString;
    private int infoInt;
    private long infoLong;
    @XmlJavaTypeAdapter(DateTimeXmlAdapter.class)
    private DateTime infoStartTime;

    static {
        ExperimentFactory.registerClassInFactory(EXPERIMENT_TYPE, SimpleExperiment.class.getCanonicalName());
    }

    public String getExperimentType() {
        return EXPERIMENT_TYPE;
    }

    public String getInfoString() {
        return infoString;
    }

    public void setInfoString(String infoString) {
        this.infoString = infoString;
    }

    public int getInfoInt() {
        return infoInt;
    }

    public void setInfoInt(int infoInt) {
        this.infoInt = infoInt;
    }

    public long getInfoLong() {
        return infoLong;
    }

    public void setInfoLong(long infoLong) {
        this.infoLong = infoLong;
    }

    public DateTime getInfoStartTime() {
        return infoStartTime;
    }

    public void setInfoStartTime(DateTime infoStartTime) {
        this.infoStartTime = infoStartTime;
    }

}
