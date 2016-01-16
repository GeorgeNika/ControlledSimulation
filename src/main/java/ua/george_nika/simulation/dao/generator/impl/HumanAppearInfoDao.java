package ua.george_nika.simulation.dao.generator.impl;

import org.springframework.stereotype.Repository;
import ua.george_nika.simulation.dao.TypeOfFiled;
import ua.george_nika.simulation.dao.AbstractDao;
import ua.george_nika.simulation.model.generator.impl.HumanAppearInfo;
import ua.george_nika.simulation.util.AppConst;
import ua.george_nika.simulation.util.AppLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 04.12.2015.
 */
@Repository
public class HumanAppearInfoDao extends AbstractDao {

    private static final String TABLE_NAME = "gen_human_appear_info";
    private static final String ID_NAME = "id_human_appear_info";
    private static final int QUANTITY_OF_FIELDS = 5;

    public HumanAppearInfoDao() {
        fieldTypeInTable.clear();
        fieldNameInTable.clear();

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add(ID_NAME);

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("id_generator");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("start_time_ms");

        fieldTypeInTable.add(TypeOfFiled.INT);
        fieldNameInTable.add("end_time_ms");

        fieldTypeInTable.add(TypeOfFiled.DOUBLE);
        fieldNameInTable.add("percent");
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

    public void createHumanAppearInfo(int idGenerator) {
        createEmptyRecordWithOneFieldAndGetNewId(AppConst.GEN_ID_IN_GEN_EXTRA_DATA_TABLE, idGenerator, TypeOfFiled.INT);
    }

    public void deleteHumanAppearInfo(int idHumanAppearInfo) {
        deleteRecordById(idHumanAppearInfo);
    }

    public void updateHumanAppearInfo(HumanAppearInfo humanAppearInfo) {
        List<Object> resultData = getDataFromHumanAppearInfo(humanAppearInfo);
        updateRecordDataById(humanAppearInfo.getIdHumanAppearInfo(), resultData);
    }

    public List<HumanAppearInfo> getAllHumanAppearInfoByGeneratorId(int idGenerator) {
        List<HumanAppearInfo> resultList = new ArrayList<>();
        List<List<Object>> resultDataListList = getAllRecordDataListByIntField(
                AppConst.GEN_ID_IN_GEN_EXTRA_DATA_TABLE, idGenerator);
        for (List<Object> loopData : resultDataListList) {
            resultList.add(getHumanAppearInfoFromData(loopData));
        }
        return resultList;
    }

    protected HumanAppearInfo getHumanAppearInfoFromData(List<Object> dataList) {
        HumanAppearInfo resultHAInfo = new HumanAppearInfo();
        resultHAInfo.setIdHumanAppearInfo((Integer) dataList.get(0));
        resultHAInfo.setIdGenerator((Integer) dataList.get(1));
        resultHAInfo.setStartTimeMs((Integer) dataList.get(2));
        resultHAInfo.setEndTimeMs((Integer) dataList.get(3));
        resultHAInfo.setPercent((Double) dataList.get(4));
        return resultHAInfo;
    }

    protected List<Object> getDataFromHumanAppearInfo(HumanAppearInfo humanAppearInfo) {
        List<Object> resultList = new ArrayList<>();
        resultList.add(humanAppearInfo.getIdHumanAppearInfo());
        resultList.add(humanAppearInfo.getIdGenerator());
        resultList.add(humanAppearInfo.getStartTimeMs());
        resultList.add(humanAppearInfo.getEndTimeMs());
        resultList.add(humanAppearInfo.getPercent());
        return resultList;
    }
}
