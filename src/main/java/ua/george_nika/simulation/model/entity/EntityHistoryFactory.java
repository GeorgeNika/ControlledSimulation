/**
 * used for getting experiment object
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.model.entity;

import ua.george_nika.simulation.model.entity.error.GetEntityHistoryException;
import ua.george_nika.simulation.util.AppLog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

public class EntityHistoryFactory {

    private static Map<String, String> entityHistoryClassMap = new HashMap<>();
    private static String LOGGER_NAME = AppLog.MODEL;
    private static String CLASS_NAME = EntityHistoryFactory.class.getSimpleName();

    public static void registerClassInFactory(String type, String fullClassName) {
        entityHistoryClassMap.put(type, fullClassName);
    }

    private EntityHistoryFactory() {
    }

    public static EntityHistory getEntityHistoryByType(String type) {
        try {
            return (EntityHistory) Class.forName(entityHistoryClassMap.get(type)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new GetEntityHistoryException(LOGGER_NAME, CLASS_NAME,
                    "Get entity history exception. Type -" + type, e);
        }
    }

    public static Collection<String> getAllRegisteredEntityHistoryClassName() {
        return entityHistoryClassMap.values();
    }

    public static Collection<String> getAllRegisteredEntityHistoryType() {
        return entityHistoryClassMap.keySet();
    }
}
