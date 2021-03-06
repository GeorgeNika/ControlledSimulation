/**
 * Exclusive service for generator "Route"
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.service.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.george_nika.simulation.dao.generator.impl.BusStartInfoDao;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.impl.BusStartInfo;
import ua.george_nika.simulation.model.generator.impl.RouteGenerator;
import ua.george_nika.simulation.service.error.GeneratorException;
import ua.george_nika.simulation.service.error.WrongIdException;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.List;

@Service
public class RouteService {

    private static String LOGGER_NAME = AppLog.SERVICE;
    private static String CLASS_NAME = RouteService.class.getSimpleName();

    @Autowired
    GeneratorService generatorService;

    @Autowired
    BusStartInfoDao busStartInfoDao;

    public List<BusStartInfo> getBusStartInfoList(int idGenerator) {
        try {
            Generator tempG = generatorService.getGeneratorById(idGenerator);
            RouteGenerator routeGenerator = ClassTypeUtil.getCheckedClass(tempG, RouteGenerator.class);
            return routeGenerator.getBusStartInfoList();
        } catch (RuntimeException ex) {
            throw new GeneratorException(LOGGER_NAME, CLASS_NAME,
                    "Error in get bus start info list for generator id - " + idGenerator, ex);
        }
    }

    public BusStartInfo getBusStartInfo(int idGenerator, int idBusStartInfo) {
        try {
            Generator tempG = generatorService.getGeneratorById(idGenerator);
            RouteGenerator routeGenerator = ClassTypeUtil.getCheckedClass(tempG, RouteGenerator.class);
            for (BusStartInfo loopInfo : routeGenerator.getBusStartInfoList()) {
                if (loopInfo.getIdBusStartInfo() == idBusStartInfo) {
                    return loopInfo;
                }
            }
            throw new WrongIdException(LOGGER_NAME, CLASS_NAME, "Wrong id. Search bus start id -" + idBusStartInfo +
                    ". Generator id -" + tempG.getIdGenerator(), new RuntimeException());
        } catch (RuntimeException ex) {
            throw new GeneratorException(LOGGER_NAME, CLASS_NAME, "Error in get bus start info id - "
                    + idBusStartInfo + " for generator id - " + idGenerator, ex);
        }
    }

    public void createNewBusStartInfo(int idGenerator) {
        try {
            busStartInfoDao.createNewBusStartInfo(idGenerator);
        } catch (RuntimeException ex) {
            throw new GeneratorException(LOGGER_NAME, CLASS_NAME,
                    "Error in create new bus start info for generator id - " + idGenerator, ex);
        }
    }

    public void deleteBusStartInfo(int idBusStartInfo) {
        try {
            busStartInfoDao.deleteBusStartInfo(idBusStartInfo);
        } catch (RuntimeException ex) {
            throw new GeneratorException(LOGGER_NAME, CLASS_NAME,
                    "Error in delete bus start info id - " + idBusStartInfo, ex);
        }
    }

    public void updateBusStartInfo(BusStartInfo busStartInfo) {
        try {
            busStartInfoDao.updateBusStartInfo(busStartInfo);
        } catch (RuntimeException ex) {
            throw new GeneratorException(LOGGER_NAME, CLASS_NAME, "Error in update bus start info id - " +
                    busStartInfo.getIdBusStartInfo(), ex);
        }
    }
}
