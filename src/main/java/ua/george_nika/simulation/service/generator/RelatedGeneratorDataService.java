package ua.george_nika.simulation.service.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.george_nika.simulation.dao.generator.RelatedGeneratorDataDao;
import ua.george_nika.simulation.model.generator.RelatedGeneratorData;
import ua.george_nika.simulation.service.error.GeneratorException;
import ua.george_nika.simulation.util.AppLog;

import java.util.List;

/**
 * Created by george on 20.12.2015.
 */
@Service
public class RelatedGeneratorDataService {
    private static String LOGGER_NAME = AppLog.SERVICE;
    private static String CLASS_NAME = RelatedGeneratorDataService.class.getName();

    @Autowired
    RelatedGeneratorDataDao relatedGeneratorDataDao;

    public List<RelatedGeneratorData> getAllRelatedGeneratorDataById(int idGenerator) {
        try {
            return relatedGeneratorDataDao.getAllRelatedGeneratorDataById(idGenerator);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get all related generator data for generator id - " +
                    idGenerator, ex);
            throw new GeneratorException("Error in get all related generator data for generator id - " + idGenerator);
        }
    }

    public void upPositionRelatedGeneratorData(int idGenerator, int position) {
        try {
            relatedGeneratorDataDao.upPositionToRelatedGeneratorData(idGenerator, position);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in up position - " + position +
                    " in generator id - " + idGenerator, ex);
            throw new GeneratorException("Error in up position - " + position +
                    " in generator id - " + idGenerator);
        }
    }

    public void downPositionRelatedGeneratorData(int idGenerator, int position) {
        try {
            relatedGeneratorDataDao.downPositionToRelatedGeneratorData(idGenerator, position);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in down position - " + position +
                    " in generator id - " + idGenerator, ex);
            throw new GeneratorException("Error in down position - " + position +
                    " in generator id - " + idGenerator);
        }
    }

    public void deleteRelatedGeneratorData(int idGenerator, int position) {
        try {
            relatedGeneratorDataDao.deleteRelatedGeneratorData(idGenerator, position);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in delete position - " + position +
                    " in generator id - " + idGenerator, ex);
            throw new GeneratorException("Error in delete position - " + position +
                    " in generator id - " + idGenerator);
        }
    }

    public void setDelayForRelatedGeneratorData(int idGenerator, int position, long delayMs) {
        try {
            relatedGeneratorDataDao.setDelayToRelatedGeneratorData(idGenerator, position, delayMs);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in set delay position - " + position +
                    " in generator id - " + idGenerator, ex);
            throw new GeneratorException("Error in set delay position - " + position +
                    " in generator id - " + idGenerator);
        }
    }

    public void createRelatedGeneratorData(int idGenerator, int idRelatedGenerator) {
        try {
            relatedGeneratorDataDao.createRelatedGeneratorData(idGenerator, idRelatedGenerator);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in create new related generator id - " + idRelatedGenerator +
                    " for generator id - " + idGenerator, ex);
            throw new GeneratorException("Error in create new related generator id - " + idRelatedGenerator +
                    " for generator id - " + idGenerator);
        }
    }

    public void updateRelatedGeneratorData(int idGenerator, int idRelatedGenerator, int position) {
        try {
            relatedGeneratorDataDao.updateRelatedGeneratorData(idGenerator, idRelatedGenerator, position);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in update related generator id - " + idRelatedGenerator +
                    " in position - " + position + " for generator id - " + idGenerator, ex);
            throw new GeneratorException("Error in update related generator id - " + idRelatedGenerator +
                    " in position - " + position + " for generator id - " + idGenerator);
        }
    }
}
