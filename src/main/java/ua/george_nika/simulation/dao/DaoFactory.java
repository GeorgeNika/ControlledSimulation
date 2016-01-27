/**
 * used for getting dao object of :
 * experiment extra class
 * history extra class
 * entity info extra class
 * for all histories (common and extra)
 * <p>
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.dao;

import ua.george_nika.simulation.dao.entity.EntityHistoryDao;
import ua.george_nika.simulation.dao.entity.EntityHistoryExtraDao;
import ua.george_nika.simulation.dao.entity.EntityInfoExtraDao;
import ua.george_nika.simulation.dao.entity.impl.EntityHistoryDaoImpl;
import ua.george_nika.simulation.dao.error.GetDaoException;
import ua.george_nika.simulation.dao.experiment.ExperimentExtraDao;
import ua.george_nika.simulation.dao.experiment.ExperimentHistoryDao;
import ua.george_nika.simulation.dao.experiment.ExperimentHistoryExtraDao;
import ua.george_nika.simulation.dao.experiment.impl.ExperimentHistoryDaoImpl;
import ua.george_nika.simulation.dao.generator.GeneratorExtraDao;
import ua.george_nika.simulation.dao.generator.GeneratorHistoryDao;
import ua.george_nika.simulation.dao.generator.GeneratorHistoryExtraDao;
import ua.george_nika.simulation.dao.generator.impl.GeneratorHistoryDaoImpl;
import ua.george_nika.simulation.util.AppLog;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")

public class DaoFactory {

    private static Map<String, String> experimentExtraDaoClassMap = new HashMap<>();
    private static Map<String, String> generatorExtraDaoClassMap = new HashMap<>();
    private static Map<String, String> entityInfoExtraDaoClassMap = new HashMap<>();

    private static Map<String, String> experimentHistoryExtraDaoClassMap = new HashMap<>();
    private static Map<String, String> generatorHistoryExtraDaoClassMap = new HashMap<>();
    private static Map<String, String> entityHistoryExtraDaoClassMap = new HashMap<>();

    private static final String LOGGER_NAME = AppLog.DAO;
    private static final String CLASS_NAME = DaoFactory.class.getSimpleName();

    private DaoFactory() {
    }


    public static void registerExperimentExtraClassInFactory(String type, String fullClassName) {
        experimentExtraDaoClassMap.put(type, fullClassName);
    }

    public static void registerGeneratorExtraClassInFactory(String type, String fullClassName) {
        generatorExtraDaoClassMap.put(type, fullClassName);
    }

    public static void registerEntityInfoExtraClassInFactory(String type, String fullClassName) {
        entityInfoExtraDaoClassMap.put(type, fullClassName);
    }

    public static void registerExperimentHistoryExtraClassInFactory(String type, String fullClassName) {
        experimentHistoryExtraDaoClassMap.put(type, fullClassName);
    }

    public static void registerGeneratorHistoryExtraClassInFactory(String type, String fullClassName) {
        generatorHistoryExtraDaoClassMap.put(type, fullClassName);
    }

    public static void registerEntityHistoryExtraClassInFactory(String type, String fullClassName) {
        entityHistoryExtraDaoClassMap.put(type, fullClassName);
    }

    public static ExperimentExtraDao getExperimentExtraDaoByType(String type) {
        try {
            return (ExperimentExtraDao) Class.forName(experimentExtraDaoClassMap.get(type)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new GetDaoException(LOGGER_NAME, CLASS_NAME,
                    "Exception in get experiment extra dao. Type - " + type, e);
        }
    }

    public static GeneratorExtraDao getGeneratorExtraDaoByType(String type) {
        try {
            return (GeneratorExtraDao) Class.forName(generatorExtraDaoClassMap.get(type)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new GetDaoException(LOGGER_NAME, CLASS_NAME,
                    "Exception in get generator extra dao. Type - " + type, e);
        }
    }

    public static EntityInfoExtraDao getEntityInfoDaoByType(String type) {
        try {
            return (EntityInfoExtraDao) Class.forName(entityInfoExtraDaoClassMap.get(type)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new GetDaoException(LOGGER_NAME, CLASS_NAME,
                    "Exception in get entityInfo extra dao. Type - " + type, e);
        }
    }

    public static ExperimentHistoryDao getExperimentHistoryDao() {
        return new ExperimentHistoryDaoImpl();
    }

    public static GeneratorHistoryDao getGeneratorHistoryDao() {
        return new GeneratorHistoryDaoImpl();
    }

    public static EntityHistoryDao getEntityHistoryDao() {
        return new EntityHistoryDaoImpl();
    }

    public static ExperimentHistoryExtraDao getExperimentHistoryExtraDaoByType(String type) {
        try {
            return (ExperimentHistoryExtraDao) Class.forName(experimentHistoryExtraDaoClassMap.get(type)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new GetDaoException(LOGGER_NAME, CLASS_NAME,
                    "Exception in get experiment history extra dao. Type - " + type, e);
        }
    }

    public static GeneratorHistoryExtraDao getGeneratorHistoryExtraDaoByType(String type) {
        try {
            return (GeneratorHistoryExtraDao) Class.forName(generatorHistoryExtraDaoClassMap.get(type)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new GetDaoException(LOGGER_NAME, CLASS_NAME,
                    "Exception in get generator history extra dao. Type - " + type, e);
        }
    }

    public static EntityHistoryExtraDao getEntityHistoryExtraDaoByType(String type) {
        try {
            return (EntityHistoryExtraDao) Class.forName(entityHistoryExtraDaoClassMap.get(type)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new GetDaoException(LOGGER_NAME, CLASS_NAME,
                    "Exception in get entity history extra dao. Type - " + type, e);
        }
    }
}
