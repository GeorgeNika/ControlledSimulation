package ua.george_nika.simulation.model.entity;

import ua.george_nika.simulation.model.entity.error.GetEntityInfoException;
import ua.george_nika.simulation.util.AppLog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by george on 04.12.2015.
 */
public class EntityInfoFactory {

    private static Map<String, String> entityInfoClassMap = new HashMap<String, String>();
    private static String LOGGER_NAME = AppLog.MODEL;
    private static String CLASS_NAME = EntityInfoFactory.class.getSimpleName();

    public static void registerClassInFactory(String type, String fullClassName) {
        entityInfoClassMap.put(type, fullClassName);
    }

    private EntityInfoFactory() {
    }

    public static EntityInfo getEntityInfoByType(String type) {
        try {
            return  (EntityInfo) Class.forName(entityInfoClassMap.get(type)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Get entity info exception. Type -" + type, e);
            throw new GetEntityInfoException("Type -" + type, e);
        }
    }

    public static Collection<String> getAllRegisteredEntityInfoClassName() {
        return entityInfoClassMap.values();
    }

    public static Collection<String> getAllRegisteredEntityInfoType() {
        return entityInfoClassMap.keySet();
    }
}
