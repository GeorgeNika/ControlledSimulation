package ua.george_nika.simulation.model.experiment;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;

/**
 * Created by george on 05.01.2016.
 */
public interface ExperimentHistory {


    void setInitialExperimentHistoryData(Experiment experiment);

    void updateExperimentHistoryData(Experiment experiment);

    String getLogIdentifyMessage();




    public int getIdExperimentHistory();

    public void setIdExperimentHistory(int idExperimentHistory);

    public int getIdExperiment();

    public void setIdExperiment(int idExperiment);

    public String getExperimentName();

    public void setExperimentName(String experimentName);

    public String getExperimentType();

    public void setExperimentType(String experimentType);

    public int getGeneratorQuantity() ;

    public void setGeneratorQuantity(int generatorQuantity) ;

    public DateTime getExecuteDate();

    public void setExecuteDate(DateTime executeDate);

    public DateTime getExperimentStart();

    public void setExperimentStart(DateTime experimentStart);

    public DateTime getExperimentEnd();

    public void setExperimentEnd(DateTime experimentEnd);

    public MutableDateTime getLastPoint();

    public void setLastPoint(MutableDateTime lastPoint);

    public String getLoggerName();

    public void setLoggerName(String loggerName);

    public String getLogFile();

    public void setLogFile(String logFile);

    public String getXmlFile();

    public void setXmlFile(String xmlFile);
}
