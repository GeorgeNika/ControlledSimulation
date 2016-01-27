/**
 * used for getting entity object
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.model.entity;

import ua.george_nika.simulation.model.entity.error.GetEntityException;
import ua.george_nika.simulation.util.AppLog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

public class EntityFactory {

    private static Map<String, String> entityClassMap = new HashMap<>();
    private static String LOGGER_NAME = AppLog.MODEL;
    private static String CLASS_NAME = EntityFactory.class.getSimpleName();

    public static void registerClassInFactory(String type, String fullClassName) {
        entityClassMap.put(type, fullClassName);
    }

    private EntityFactory() {
    }

    public static Entity getEntityByType(String type) {
        try {
            return (Entity) Class.forName(entityClassMap.get(type)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new GetEntityException(LOGGER_NAME, CLASS_NAME, "Get entity exception. Type -" + type, e);
        }
    }

    public static Collection<String> getAllRegisteredEntityClassName() {
        return entityClassMap.values();
    }

    public static Collection<String> getAllRegisteredEntityType() {
        return entityClassMap.keySet();
    }
}
