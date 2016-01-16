package ua.george_nika.simulation.service.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.george_nika.simulation.dao.generator.impl.HumanAppearInfoDao;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.impl.HumanAppearInfo;
import ua.george_nika.simulation.model.generator.impl.StationGenerator;
import ua.george_nika.simulation.service.error.GeneratorException;
import ua.george_nika.simulation.service.error.WrongIdException;
import ua.george_nika.simulation.service.error.WrongTypeException;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.List;

/**
 * Created by george on 20.12.2015.
 */
@Service
public class StationService {

    private static String LOGGER_NAME = AppLog.SERVICE;
    private static String CLASS_NAME = StationService.class.getSimpleName();

    @Autowired
    GeneratorService generatorService;

    @Autowired
    HumanAppearInfoDao humanAppearInfoDao;

    public List<HumanAppearInfo> getHumanAppearInfoList(int idGenerator) {
        try {
            Generator tempG = generatorService.getGeneratorById(idGenerator);
            StationGenerator stationGenerator = ClassTypeUtil.getCheckedClass(tempG, StationGenerator.class);
            return stationGenerator.getHumanAppearInfoList();
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get human appear info list for generator id - " +
                    idGenerator, ex);
            throw new GeneratorException("Error in get human appear info list for generator id - " + idGenerator);
        }
    }

    public HumanAppearInfo getHumanAppearInfo(int idGenerator, int idHumanAppearInfo) {
        try {
            Generator tempG = generatorService.getGeneratorById(idGenerator);
            StationGenerator stationGenerator = ClassTypeUtil.getCheckedClass(tempG, StationGenerator.class);
            for (HumanAppearInfo loopInfo : stationGenerator.getHumanAppearInfoList()) {
                if (loopInfo.getIdHumanAppearInfo() == idHumanAppearInfo) {
                    return loopInfo;
                }
            }
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Wrong id. Search human appear id -" + idHumanAppearInfo +
                    ". Generator id -" + tempG.getIdGenerator());
            throw new WrongIdException();
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in get human appear info id - " + idHumanAppearInfo +
                    " for generator id - " + idGenerator, ex);
            throw new GeneratorException("Error in get human appear info id - " + idHumanAppearInfo +
                    " for generator id - " + idGenerator);
        }
    }

    public void createNewHumanAppearInfo(int idGenerator) {
        try {
            humanAppearInfoDao.createHumanAppearInfo(idGenerator);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in create new human appear info for generator id - " +
                    idGenerator, ex);
            throw new GeneratorException("Error in create new human appear info for generator id - " +
                    idGenerator);
        }
    }

    public void deleteHumanAppearInfo(int idHumanAppearInfo) {
        try {
            humanAppearInfoDao.deleteHumanAppearInfo(idHumanAppearInfo);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in delete human appear info id - " + idHumanAppearInfo, ex);
            throw new GeneratorException("Error in delete human appear info id - " + idHumanAppearInfo);
        }
    }

    public void updateHumanAppearInfo(HumanAppearInfo humanAppearInfo) {
        try {
            humanAppearInfoDao.updateHumanAppearInfo(humanAppearInfo);
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in update human appear info id - " +
                    humanAppearInfo.getIdHumanAppearInfo(), ex);
            throw new GeneratorException("Error in update human appear info id - " +
                    humanAppearInfo.getIdHumanAppearInfo());
        }
    }


}
