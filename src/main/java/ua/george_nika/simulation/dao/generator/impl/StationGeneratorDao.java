package ua.george_nika.simulation.dao.generator.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoFactory;
import ua.george_nika.simulation.dao.generator.GeneratorExtraDao;
import ua.george_nika.simulation.dao.error.WrongDataDaoException;
import ua.george_nika.simulation.dao.generator.abstr.AbstractGeneratorExtraDao;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.impl.HumanAppearInfo;
import ua.george_nika.simulation.model.generator.impl.StationGenerator;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import java.util.List;

/**
 * Created by george on 29.12.2015.
 */
@Repository
public class StationGeneratorDao extends AbstractGeneratorExtraDao {

    private static String LOGGER_NAME = AppLog.DAO;
    private static String CLASS_NAME = StationGeneratorDao.class.getSimpleName();

    private HumanAppearInfoDao humanAppearInfoDao;

    static {
        DaoFactory.registerGeneratorExtraClassInFactory(StationGenerator.GENERATOR_TYPE,
                StationGeneratorDao.class.getCanonicalName());
    }

    @Override
    public void addExtraDataToGenerator(Generator generator) {

        humanAppearInfoDao = new HumanAppearInfoDao();
        List<HumanAppearInfo> resultHAInfoList =
                humanAppearInfoDao.getAllHumanAppearInfoByGeneratorId(generator.getIdGenerator());
        StationGenerator stationGenerator = ClassTypeUtil.getCheckedClass(generator, StationGenerator.class);
        stationGenerator.setHumanAppearInfoList(resultHAInfoList);
    }
}
