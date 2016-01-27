/**
 * used for getting experiment history object
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.model.experiment;

import ua.george_nika.simulation.model.experiment.error.GetExperimentHistoryException;
import ua.george_nika.simulation.util.AppLog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

public class ExperimentHistoryFactory {
    private static Map<String, String> experimentHistoryClassMap = new HashMap<>();
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
            throw new GetExperimentHistoryException(LOGGER_NAME, CLASS_NAME,
                    "Get experiment history exception. Type - " + type, e);
        }
    }

    public static Collection<String> getAllRegisteredExperimentHistoryClassName() {
        return experimentHistoryClassMap.values();
    }

    public static Collection<String> getAllRegisteredExperimentHistoryType() {
        return experimentHistoryClassMap.keySet();
    }
}
