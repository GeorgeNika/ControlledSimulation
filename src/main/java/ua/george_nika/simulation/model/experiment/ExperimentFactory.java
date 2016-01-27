/**
 * used for getting experiment object
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.model.experiment;

import ua.george_nika.simulation.model.experiment.error.GetExperimentException;
import ua.george_nika.simulation.util.AppLog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

public class ExperimentFactory {

    private static Map<String, String> experimentClassMap = new HashMap<>();
    private static String LOGGER_NAME = AppLog.MODEL;
    private static String CLASS_NAME = ExperimentFactory.class.getSimpleName();

    public static void registerClassInFactory(String type, String fullClassName) {
        experimentClassMap.put(type, fullClassName);
    }

    private ExperimentFactory() {
    }

    public static Experiment getExperimentByType(String type) {
        try {
            return (Experiment) Class.forName(experimentClassMap.get(type)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new GetExperimentException(LOGGER_NAME, CLASS_NAME, "Get experiment exception. Type -" + type, e);
        }
    }

    public static Collection<String> getAllRegisteredExperimentClassName() {
        return experimentClassMap.values();
    }

    public static Collection<String> getAllRegisteredExperimentType() {
        return experimentClassMap.keySet();
    }
}
