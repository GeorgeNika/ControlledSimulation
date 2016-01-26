/**
 * Filter for history records
 */

package ua.george_nika.simulation.dao.filter;

import java.util.List;

public interface DaoFilter {

    // this filter work only for integer

    void setFilterInfo(List<String> filterFieldNameList, List<Integer> filterFiledValueList);
}
