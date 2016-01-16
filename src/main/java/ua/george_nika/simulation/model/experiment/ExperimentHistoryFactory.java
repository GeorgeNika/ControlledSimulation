package ua.george_nika.simulation.model.experiment;

import ua.george_nika.simulation.model.experiment.error.GetExperimentHistoryException;
import ua.george_nika.simulation.util.AppLog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by george on 04.12.2015.
 */
public class ExperimentHistoryFactory {
    private static Map<String, String> experimentHistoryClassMap = new HashMap<String, String>();
    private static String LOGGER_NAME = AppLog.MODEL;
    private static String CLASS_NAME = ExperimentHistoryFactory.class.getSimpleName();

    public static void registerClassInFactory(String type, String fullClassName) {
        experimentHistoryClassMap.put(type, fullClassName);
    }

    private ExperimentHistoryFactory() {
    }

    public static ExperimentHistory getExperimentHistoryByType(String type) {
        try {
            return (ExperimentHistory) Class.forName(experimentHistoryClassMap.get(type)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Get experiment history exception. Type - " + type, e);
            throw new GetExperimentHistoryException("Get experiment history exception. Type - " + type, e);
        }
    }

    public static Collection<String> getAllRegisteredExperimentHistoryClassName() {
        return experimentHistoryClassMap.values();
    }

    public static Collection<String> getAllRegisteredExperimentHistoryType() {
        return experimentHistoryClassMap.keySet();
    }
}
