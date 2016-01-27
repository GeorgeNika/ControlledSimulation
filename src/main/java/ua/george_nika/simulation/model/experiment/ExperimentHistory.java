/**
 * Interface for model object - experiment history
 */

package ua.george_nika.simulation.model.experiment;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;

public interface ExperimentHistory {


    void setInitialExperimentHistoryData(Experiment experiment);

    void updateExperimentHistoryData(Experiment experiment);

    String getLogIdentifyMessage();


    // todo in future.  Move history and log to separate function and then use AOP

    // getter and setter from abstract class

    int getIdExperimentHistory();

    void setIdExperimentHistory(int idExperimentHistory);

    int getIdExperiment();

    void setIdExperiment(int idExperiment);

    String getExperimentName();

    void setExperimentName(String experimentName);

    String getExperimentType();

    void setExperimentType(String experimentType);

    int getGeneratorQuantity();

    void setGeneratorQuantity(int generatorQuantity);

    DateTime getExecuteDate();

    void setExecuteDate(DateTime executeDate);

    DateTime getExperimentStart();

    void setExperimentStart(DateTime experimentStart);

    DateTime getExperimentEnd();

    void setExperimentEnd(DateTime experimentEnd);

    MutableDateTime getLastPoint();

    void setLastPoint(MutableDateTime lastPoint);

    String getLoggerName();

    void setLoggerName(String loggerName);

    String getLogFile();

    void setLogFile(String logFile);

    String getXmlFile();

    void setXmlFile(String xmlFile);
}
