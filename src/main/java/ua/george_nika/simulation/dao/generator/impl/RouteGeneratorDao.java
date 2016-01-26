/**
 * work`s with "route" generator table
 * now it does not exist
 */

package ua.george_nika.simulation.dao.generator.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.generator.abstr.AbstractGeneratorExtraDao;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.impl.BusStartInfo;
import ua.george_nika.simulation.model.generator.impl.RouteGenerator;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.List;

@SuppressWarnings({"unused","FieldCanBeLocal"})

@Repository
public class RouteGeneratorDao extends AbstractGeneratorExtraDao {

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = BusStartInfoDao.class.getSimpleName();

    private BusStartInfoDao busStartInfoDao;

    static {
        DaoFactory.registerGeneratorExtraClassInFactory(RouteGenerator.GENERATOR_TYPE,
                RouteGeneratorDao.class.getCanonicalName());
    }

    @Override
    public void addExtraDataToGenerator(Generator generator) {
        busStartInfoDao = new BusStartInfoDao();
        List<BusStartInfo> resultBSInfoList =
                busStartInfoDao.getAllBusStartInfoByGeneratorId(generator.getIdGenerator());
        RouteGenerator routeGenerator = ClassTypeUtil.getCheckedClass(generator, RouteGenerator.class);
        routeGenerator.setBusStartInfoList(resultBSInfoList);
    }
}
