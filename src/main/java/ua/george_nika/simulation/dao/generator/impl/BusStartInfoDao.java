package ua.george_nika.simulation.dao.generator.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.DaoConst;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.AbstractDao;
import ua.george_nika.simulation.model.generator.impl.BusStartInfo;
import ua.george_nika.simulation.util.AppConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 04.12.2015.
 */
@Repository
public class BusStartInfoDao extends AbstractDao {

    private static final String TABLE_NAME = "gen_bus_start_info";
    private static final String ID_NAME = "id_bus_start_info";
    private static final int QUANTITY_OF_FIELDS = 6;

    public BusStartInfoDao() {
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("id_generator");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("start_time_ms");

        fieldTypeInTable.add(TypeOfFiled.BOOLEAN);
        fieldNameInTable.add("forward_direction");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("repeat_quantity");

        fieldTypeInTable.add(TypeOfFiled.BOOLEAN);
        fieldNameInTable.add("change_direction");
    }


    protected String getTableName() {
        return TABLE_NAME;
    }

    protected String getIdName() {
        return ID_NAME;
    }

    protected int getQuantityOfFields() {
        return QUANTITY_OF_FIELDS;
    }

    public void createNewBusStartInfo(int idGenerator) {
        createEmptyRecordWithOneFieldAndGetNewId(DaoConst.GEN_ID_IN_GEN_EXTRA_DATA_TABLE, idGenerator, TypeOfFiled.INT);
    }

    public void deleteBusStartInfo(int idBusStartInfo) {
        deleteRecordById(idBusStartInfo);
    }

    public void updateBusStartInfo(BusStartInfo busStartInfo) {
        List<Object> resultData = getDataFromBusStartInfo(busStartInfo);
        updateRecordDataById(busStartInfo.getIdBusStartInfo(), resultData);
    }

    public List<BusStartInfo> getAllBusStartInfoByGeneratorId(int idGenerator) {
        List<BusStartInfo> resultList = new ArrayList<>();
        List<List<Object>> resultDataListList = getAllRecordDataListByIntField(
                DaoConst.GEN_ID_IN_GEN_EXTRA_DATA_TABLE, idGenerator);
        for (List<Object> loopData : resultDataListList) {
            resultList.add(getBusStartInfoFromData(loopData));
        }
        return resultList;
    }

    protected BusStartInfo getBusStartInfoFromData(List<Object> dataList) {

        BusStartInfo resultStartInfo = new BusStartInfo();
        resultStartInfo.setIdBusStartInfo((Integer) dataList.get(0));
        resultStartInfo.setIdGenerator((Integer) dataList.get(1));
        resultStartInfo.setStartTimeMs((Integer) dataList.get(2));
        resultStartInfo.setForwardDirection((Boolean) dataList.get(3));
        resultStartInfo.setRepeatQuantity((Integer) dataList.get(4));
        resultStartInfo.setChangeDirection((Boolean) dataList.get(5));
        return resultStartInfo;
    }

    protected List<Object> getDataFromBusStartInfo(BusStartInfo busStartInfo) {
        List<Object> resultList = new ArrayList<>();
        resultList.add(busStartInfo.getIdBusStartInfo());
        resultList.add(busStartInfo.getIdGenerator());
        resultList.add(busStartInfo.getStartTimeMs());
        resultList.add(busStartInfo.isForwardDirection());
        resultList.add(busStartInfo.getRepeatQuantity());
        resultList.add(busStartInfo.isChangeDirection());
        return resultList;
    }
}
