/**
 * used for getting extra controllers
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller;

import ua.george_nika.simulation.controller.entity.EntityInfoExtraController;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.controller.entity.EntityExtraController;
import ua.george_nika.simulation.controller.error.NoNormalExtraControllerRegistered;
import ua.george_nika.simulation.controller.experiment.ExperimentExtraController;
import ua.george_nika.simulation.controller.generator.GeneratorExtraController;

import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {

    private static Map<String, String> experimentControllerClassMap = new HashMap<>();
    private static Map<String, String> generatorControllerClassMap = new HashMap<>();
    private static Map<String, String> entityControllerClassMap = new HashMap<>();
    private static Map<String, String> entityInfoControllerClassMap = new HashMap<>();

    private static String LOGGER_NAME = AppLog.CONTROLLER;
    private static String CLASS_NAME = ControllerFactory.class.getSimpleName();

    private ControllerFactory() {
    }

    public static void registerExperimentControllerClassInFactory(String type, String fullClassName) {
        experimentControllerClassMap.put(type, fullClassName);
    }

    public static void registerGeneratorControllerClassInFactory(String type, String fullClassName) {
        generatorControllerClassMap.put(type, fullClassName);
    }

    public static void registerEntityControllerClassInFactory(String type, String fullClassName) {
        entityControllerClassMap.put(type, fullClassName);
    }

    public static void registerEntityInfoControllerClassInFactory(String type, String fullClassName) {
        entityInfoControllerClassMap.put(type, fullClassName);
    }

    public static ExperimentExtraController getExperimentExtraControllerByType(String type) {
        try {
            return (ExperimentExtraController) Class.forName(experimentControllerClassMap.get(type)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Get controller exception. Type -" + type, e);
            throw new NoNormalExtraControllerRegistered("Get controller exception. Type -" + type, e);
        }
    }

    public static GeneratorExtraController getGeneratorExtraControllerByType(String type) {
        try {
            return (GeneratorExtraController) Class.forName(generatorControllerClassMap.get(type)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Get controller exception. Type -" + type, e);
            throw new NoNormalExtraControllerRegistered("Get controller exception. Type -" + type, e);
        }
    }

    public static EntityExtraController getEntityExtraControllerByType(String type) {
        try {
            return (EntityExtraController) Class.forName(entityControllerClassMap.get(type)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Get controller exception. Type -" + type, e);
            throw new NoNormalExtraControllerRegistered("Get controller exception. Type -" + type, e);
        }
    }

    public static EntityInfoExtraController getEntityInfoExtraControllerByType(String type) {
        try {
            return (EntityInfoExtraController) Class.forName(entityInfoControllerClassMap.get(type)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Get controller exception. Type -" + type, e);
            throw new NoNormalExtraControllerRegistered("Get controller exception. Type -" + type, e);
        }
    }

}
