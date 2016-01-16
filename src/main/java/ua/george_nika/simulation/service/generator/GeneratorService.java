package ua.george_nika.simulation.service.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.generator.GeneratorDao;
import ua.george_nika.simulation.dao.generator.GeneratorExtraDao;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.RelatedGeneratorData;
import ua.george_nika.simulation.service.entity.EntityInfoService;
import ua.george_nika.simulation.service.error.GeneratorException;
import ua.george_nika.simulation.util.AppLog;

import java.util.List;

/**
 * Created by george on 08.12.2015.
 */
@Service
public class GeneratorService {
    private static String LOGGER_NAME = AppLog.SERVICE;
    private static String CLASS_NAME = GeneratorService.class.getName();

    @Autowired
    GeneratorDao generatorDao;

    @Autowired
    RelatedGeneratorDataService relatedGeneratorDataService;

    @Autowired
    EntityInfoService entityInfoService;

    GeneratorExtraDao generatorExtraDao;

    public Generator getGeneratorById(int idGenerator) {
        try {
            Generator resultGenerator;
            resultGenerator = generatorDao.getLazyGeneratorById(idGenerator);
            addRelatedGeneratorDataToGenerator(resultGenerator);
            addEntityInfoToGenerator(resultGenerator);
            addExtraDataToGenerator(resultGenerator);
            return resultGenerator;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get generator by id - " + idGenerator, ex);
            throw new GeneratorException("Error in get generator by id - " + idGenerator);
        }
    }

    public Generator getLazyGeneratorById(int idGenerator) {
        try {
            Generator resultGenerator;
            resultGenerator = generatorDao.getLazyGeneratorById(idGenerator);
            return resultGenerator;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get lazy generator by id - " + idGenerator, ex);
            throw new GeneratorException("Error in get lazy generator by id - " + idGenerator);
        }
    }

    public List<Generator> getAllLazyGenerator() {
        try {
            List<Generator> resultGeneratorList;
            resultGeneratorList = generatorDao.getAllLazyGenerator();
            return resultGeneratorList;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get all lazy generator", ex);
            throw new GeneratorException("Error in get all lazy generator");
        }
    }

    public List<Generator> getAllGeneratorByExperimentId(int idExperiment) {
        try {
            List<Generator> resultGeneratorList;
            resultGeneratorList = generatorDao.getAllLazyGeneratorByExperimentId(idExperiment);
            for (Generator loopGenerator : resultGeneratorList) {
                addRelatedGeneratorDataToGenerator(loopGenerator);
                addEntityInfoToGenerator(loopGenerator);
                addExtraDataToGenerator(loopGenerator);
            }
            return resultGeneratorList;
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get all generator by experiment id - " + idExperiment, ex);
            throw new GeneratorException("Error in get all generator by experiment id - " + idExperiment);
        }
    }


    public void createGeneratorByType(String generatorType) {
        try {
            generatorDao.createdEmptyGeneratorByTypeAndGetNewId(generatorType);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in create new generator by type - " + generatorType, ex);
            throw new GeneratorException("Error in create new generator by type - " + generatorType);
        }
    }

    public void updateLazyGenerator(Generator generator) {
        try {
            generatorDao.updateLazyGenerator(generator);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in update lazy generator id - " + generator.getIdGenerator(), ex);
            throw new GeneratorException("Error in update lazy generator id - " + generator.getIdGenerator());
        }
    }

    public void deleteGeneratorById(int idGenerator) {
        try {
            generatorDao.deleteLazyGeneratorById(idGenerator);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in delete generator by id - " + idGenerator, ex);
            throw new GeneratorException("Error in delete generator by id - " + idGenerator);
        }
    }


    protected void addExtraDataToGenerator(Generator generator) {
        generatorExtraDao = DaoFactory.getGeneratorExtraDaoByType(generator.getGeneratorType());
        generatorExtraDao.addExtraDataToGenerator(generator);
    }

    protected void addEntityInfoToGenerator(Generator generator) {
        if ((generator.getEntityType() != null) && (generator.getIdEntityInfo() != 0)) {
            EntityInfo tempEInfo = entityInfoService.getEntityInfoByEntityTypeById(
                    generator.getEntityType(), generator.getIdEntityInfo());
            generator.setEntityInfo(tempEInfo);
        }
    }

    protected void addRelatedGeneratorDataToGenerator(Generator generator) {
        List<RelatedGeneratorData> tempRGDList
                = relatedGeneratorDataService.getAllRelatedGeneratorDataById(generator.getIdGenerator());
        generator.setRelatedGeneratorDataList(tempRGDList);
    }
}
