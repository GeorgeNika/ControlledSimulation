/**
 * Returns sorted collection before they go to view(JSP)
 * after lecture  JavaDoc + UnitTest = Documentation
 */
package ua.george_nika.simulation.util;

import ua.george_nika.simulation.controller.light_ajax_info.generator.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUtil {

    private SortUtil() {
    }

    public static void sortLightGeneratorRunInfoList(List<LightGeneratorRunInfo> generatorList) {
        // sort by type and name
        Collections.sort(generatorList, new Comparator<LightGeneratorRunInfo>() {
            @Override
            public int compare(LightGeneratorRunInfo o1, LightGeneratorRunInfo o2) {
                if (o1.getGeneratorType().equals(o2.getGeneratorType())) {
                    return o1.getGeneratorName().compareTo(o2.getGeneratorName());
                }
                return o1.getGeneratorType().compareTo(o2.getGeneratorType());
            }
        });
    }

    public static void sortLightGeneratorList(List<LightGenerator> lightGeneratorList) {
        // sort by type and name
        Collections.sort(lightGeneratorList, new Comparator<LightGenerator>() {
            @Override
            public int compare(LightGenerator o1, LightGenerator o2) {
                if (o1.getGeneratorType().equals(o2.getGeneratorType())) {
                    return o1.getGeneratorName().compareTo(o2.getGeneratorName());
                }
                return o1.getGeneratorType().compareTo(o2.getGeneratorType());
            }
        });
    }

    public static void sortLightGeneratorHistoryList(List<LightGeneratorHistory> lightGeneratorHistoryList) {
        // sort by type and name
        Collections.sort(lightGeneratorHistoryList, new Comparator<LightGeneratorHistory>() {
            @Override
            public int compare(LightGeneratorHistory o1, LightGeneratorHistory o2) {
                if (o1.getGeneratorType().equals(o2.getGeneratorType())) {
                    return o1.getGeneratorName().compareTo(o2.getGeneratorName());
                }
                return o1.getGeneratorType().compareTo(o2.getGeneratorType());
            }
        });
    }

    public static void sortLightBusStartInfoList(List<LightBusStartInfo> lightBusStartInfoList) {
        // sort by start time
        Collections.sort(lightBusStartInfoList, new Comparator<LightBusStartInfo>() {
            @Override
            public int compare(LightBusStartInfo o1, LightBusStartInfo o2) {
                return Integer.compare(o1.getStartTimeMs(), o2.getStartTimeMs());
            }
        });
    }

    public static void sortLightHumanAppearInfoList(List<LightHumanAppearInfo> lightHumanAppearInfoList) {
        //sort by start time
        Collections.sort(lightHumanAppearInfoList, new Comparator<LightHumanAppearInfo>() {
            @Override
            public int compare(LightHumanAppearInfo o1, LightHumanAppearInfo o2) {
                return Integer.compare(o1.getStartTimeMs(), o2.getStartTimeMs());
            }
        });
    }
}
