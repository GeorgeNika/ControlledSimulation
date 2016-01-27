/**
 * used for getting generator object
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.model.generator;

import ua.george_nika.simulation.model.generator.error.GetGeneratorException;
import ua.george_nika.simulation.util.AppLog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

public class GeneratorFactory {

    private static Map<String, String> generatorClassMap = new HashMap<>();
    private static String LOGGER_NAME = AppLog.MODEL;
    private static String CLASS_NAME = GeneratorFactory.class.getSimpleName();

    public static void registerClassInFactory(String type, String fullClassName) {
        generatorClassMap.put(type, fullClassName);
    }

    private GeneratorFactory() {
    }

    public static Generator getGeneratorByType(String type) {
        try {
            return (Generator) Class.forName(generatorClassMap.get(type)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new GetGeneratorException(LOGGER_NAME, CLASS_NAME, "Get generator exception. Type -" + type, e);
        }
    }

    public static Collection<String> getAllRegisteredGeneratorClassName() {
        return generatorClassMap.values();
    }

    public static Collection<String> getAllRegisteredGeneratorType() {
        return generatorClassMap.keySet();
    }
}
