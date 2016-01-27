/**
 * used for getting generator history object
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.model.generator;

import ua.george_nika.simulation.model.generator.error.GetGeneratorHistoryException;
import ua.george_nika.simulation.util.AppLog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

public class GeneratorHistoryFactory {
    private static Map<String, String> generatorHistoryClassMap = new HashMap<>();
    private static String LOGGER_NAME = AppLog.MODEL;
    private static String CLASS_NAME = GeneratorHistoryFactory.class.getSimpleName();

    public static void registerClassInFactory(String type, String fullClassName) {
        generatorHistoryClassMap.put(type, fullClassName);
    }

    private GeneratorHistoryFactory() {
    }

    public static GeneratorHistory getGeneratorHistoryByType(String type) {
        GeneratorHistory resultGeneratorHistory = null;
        try {
            return (GeneratorHistory) Class.forName(generatorHistoryClassMap.get(type)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new GetGeneratorHistoryException(LOGGER_NAME, CLASS_NAME,
                    "Get generator history exception. Type - " + type, e);
        }
    }

    public static Collection<String> getAllRegisteredExperimentHistoryClassName() {
        return generatorHistoryClassMap.values();
    }

    public static Collection<String> getAllRegisteredExperimentHistoryType() {
        return generatorHistoryClassMap.keySet();
    }
}
