package ua.george_nika.simulation.dao.filter;

import java.util.List;

/**
 * Created by george on 22.01.2016.
 */
public interface DaoFilter {

    // filter work only for integer

    void setFilterInfo(List<String> filterFieldNameList, List<Integer> filterFiledValueList);
}
