/**
 * springMVC form
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.form;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import ua.george_nika.simulation.model.experiment.impl.SimpleExperiment;
import ua.george_nika.simulation.util.AppConst;
import ua.george_nika.simulation.util.TimeToStringUtil;

@SuppressWarnings("unused")

public class SimpleExperimentExtraDataForm {

    private String infoString;
    private int infoInt;
    private long infoLong;
    private String infoStartTime;

    public SimpleExperimentExtraDataForm() {
    }

    public SimpleExperimentExtraDataForm(SimpleExperiment simpleExperiment) {
        this.infoString = simpleExperiment.getInfoString();
        this.infoInt = simpleExperiment.getInfoInt();
        this.infoLong = simpleExperiment.getInfoLong();
        this.infoStartTime = TimeToStringUtil.getSafeString(simpleExperiment.getInfoStartTime());
    }

    public void updateExtraDataExperiment(SimpleExperiment simpleExperiment) {
        simpleExperiment.setInfoString(infoString);
        simpleExperiment.setInfoInt(infoInt);
        simpleExperiment.setInfoLong(infoLong);
        DateTimeFormatter formatter = DateTimeFormat.forPattern(AppConst.DATE_TIME_FORMAT);
        simpleExperiment.setInfoStartTime(formatter.parseDateTime(infoStartTime));
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

    public String getInfoStartTime() {
        return infoStartTime;
    }

    public void setInfoStartTime(String infoStartTime) {
        this.infoStartTime = infoStartTime;
    }
}
