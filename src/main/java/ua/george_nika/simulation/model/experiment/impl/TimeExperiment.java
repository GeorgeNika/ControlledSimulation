package ua.george_nika.simulation.model.experiment.impl;

import org.springframework.stereotype.Component;
import ua.george_nika.simulation.model.entity.impl.BusRoundEntity;
import ua.george_nika.simulation.model.experiment.ExperimentFactory;
import ua.george_nika.simulation.model.experiment.abstr.AbstractExperiment;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.RelatedGeneratorData;
import ua.george_nika.simulation.model.generator.error.NoSuchGenerator;
import ua.george_nika.simulation.model.generator.impl.RouteGenerator;
import ua.george_nika.simulation.model.generator.impl.StationGenerator;
import ua.george_nika.simulation.service.experiment.ExperimentHistoryService;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.*;

/**
 * Created by george on 15.01.2016.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class TimeExperiment extends AbstractExperiment {

    private static final String CLASS_NAME = TimeExperiment.class.getCanonicalName();

    public static final String EXPERIMENT_TYPE = "time";

    private int idStartGenerator;
    private int idEndGenerator;

    @XmlTransient
    private Map<Integer, Integer> reflectMap = new HashMap<>();
    @XmlTransient
    private Map<Integer, Integer> reverseReflectMap = new HashMap<>();


    @XmlTransient
    private long[][] timeMatrix;

    @XmlTransient
    private int[] checkedStationS;

    @XmlTransient
    private long[] currentMinTimeB;

    @XmlTransient
    private int[] previousStationC;

    static {
        ExperimentFactory.registerClassInFactory(EXPERIMENT_TYPE, TimeExperiment.class.getCanonicalName());
    }

    @Override
    public String getExperimentType() {
        return EXPERIMENT_TYPE;
    }

    public void startExecution() {
        checkIsExperimentCorrect();
        initExperimentWorkVariable();
        saveAllHistory();

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void saveAllHistory() {
        ExperimentHistoryService.saveExperimentHistory(experimentHistory);
    }

    @Override
    protected void updateAllHistory() {
        experimentHistory.updateExperimentHistoryData(this);
    }

    public int getIdStartGenerator() {
        return idStartGenerator;
    }

    public void setIdStartGenerator(int idStartGenerator) {
        this.idStartGenerator = idStartGenerator;
    }

    public int getIdEndGenerator() {
        return idEndGenerator;
    }

    public void setIdEndGenerator(int idEndGenerator) {
        this.idEndGenerator = idEndGenerator;
    }

    public long getMinTime(){
        if (currentMinTimeB== null){
            return Long.MAX_VALUE;
        }
        if (reflectMap == null){
            return Long.MAX_VALUE;
        }
        return currentMinTimeB[reflectMap.get(idEndGenerator)];
    }

    public void run() {
        createReflectMap();
        createTimeMatrix();

        calculateMinTimeWithDijkstraAlgorithm();

        endExecution();
    }

    private void createReflectMap() {
        Set<Integer> idStationSet = getIdStationSet();
        int numberInArray = 0;
        for (Integer loopId : idStationSet) {
            reflectMap.put(loopId, numberInArray);
            reverseReflectMap.put(numberInArray, loopId);
            numberInArray++;
        }
    }

    private Set<Integer> getIdStationSet() {
        Set<Integer> idStationSet = new HashSet<>();
        for (Generator loopGenerator : generatorList) {
            if (loopGenerator instanceof StationGenerator) {
                idStationSet.add(loopGenerator.getIdGenerator());
            }
        }
        return idStationSet;
    }


    private void createTimeMatrix() {
        timeMatrix = new long[reflectMap.keySet().size()][reflectMap.keySet().size()];
        // fill timeMatrix with MAX_VALUE
        for (int i = 0; i < reflectMap.keySet().size(); i++) {
            Arrays.fill(timeMatrix[i], Long.MAX_VALUE);
        }

        // look routeGenerator
        for (Generator loopGenerator : generatorList) {
            if (loopGenerator instanceof RouteGenerator) {
                fillRealTimeInTimeMatrix(loopGenerator);
            }
        }
    }

    private void fillRealTimeInTimeMatrix(Generator generator) {
        RouteGenerator routeGenerator = ClassTypeUtil.getCheckedClass(generator, RouteGenerator.class);

        // fill 2 temp Arrays
        int[] idStationInRoute = new int[routeGenerator.getRelatedGeneratorDataList().size()];
        long[] timeForNextStationInRoute = new long[routeGenerator.getRelatedGeneratorDataList().size()];
        int tempIndex;
        for (RelatedGeneratorData loopRGData : routeGenerator.getRelatedGeneratorDataList()) {
            tempIndex = loopRGData.getRelatedPosition() - 1; // position 1..10 - index 0..9
            idStationInRoute[tempIndex] = loopRGData.getIdRelatedGenerator();
            timeForNextStationInRoute[tempIndex] = loopRGData.getDelayMs();
        }

        if (generator.getEntityType().equals(BusRoundEntity.ENTITY_TYPE)) {
            // round bus situation
            int[] tempIdStationInRoute = new int[routeGenerator.getRelatedGeneratorDataList().size() * 2];
            long[] tempTimeForNextStationInRoute = new long[routeGenerator.getRelatedGeneratorDataList().size() * 2];
            for (int i = 0; i < idStationInRoute.length; i++) {
                tempIdStationInRoute[i] = idStationInRoute[i];
                tempTimeForNextStationInRoute[i] = timeForNextStationInRoute[i];
            }
            // second circle
            for (int i = 0; i < idStationInRoute.length; i++) {
                tempIdStationInRoute[i + idStationInRoute.length] = idStationInRoute[i];
                tempTimeForNextStationInRoute[i + idStationInRoute.length] = timeForNextStationInRoute[i];
            }
            idStationInRoute = tempIdStationInRoute;
            timeForNextStationInRoute = tempTimeForNextStationInRoute;
        }

        // fill time
        for (int i = 0; i < idStationInRoute.length; i++) {
            setTimeInMatrix(idStationInRoute[i], idStationInRoute[i], 0);
            long travelTime = timeForNextStationInRoute[i];  // set time from i station to i+1
            for (int j = i + 1; j < idStationInRoute.length; j++) {
                // fill time from i to j station
                setTimeInMatrix(idStationInRoute[i], idStationInRoute[j], travelTime);
                travelTime += timeForNextStationInRoute[j];  // add time from j station to j+1
            }
        }
    }

    private void setTimeInMatrix(int idStationStart, int idStationEnd, long value) {
        int i = reflectMap.get(idStationStart);
        int j = reflectMap.get(idStationEnd);

        // create symmetry matrix (not necessary)
        if (timeMatrix[i][j] != timeMatrix[j][i]) {
            AppLog.error(AppLog.MODEL, CLASS_NAME, "Error in algorithm. Non symmetry matrix");
            throw new RuntimeException("Error in algorithm");
        }
        if (timeMatrix[i][j] > value) {
            timeMatrix[i][j] = value;
            timeMatrix[j][i] = value;
        }
    }

    private void calculateMinTimeWithDijkstraAlgorithm() {
        // first step
        initializationThreeArrays();

        // second step
        iterationSearch();

        // third step
        logResult();

    }

    private void initializationThreeArrays() {
        checkedStationS = new int[reflectMap.keySet().size()];
        Arrays.fill(checkedStationS, 0);

        currentMinTimeB = new long[reflectMap.keySet().size()];
        for (int i = 0; i < currentMinTimeB.length; i++) {
            currentMinTimeB[i] = timeMatrix[reflectMap.get(idStartGenerator)][i];
        }

        previousStationC = new int[reflectMap.keySet().size()];
        Arrays.fill(previousStationC, reflectMap.get(idStartGenerator));

        checkedStationS[reflectMap.get(idStartGenerator)] = 1;
        previousStationC[reflectMap.get(idStartGenerator)] = -1;
        // use "-1" instead "0". "0" used like array index.
    }

    private void iterationSearch() {
        while (Arrays.binarySearch(checkedStationS, 0) >= 0) {
            int j = getMinTimeFromNoneCheckedStation();
            checkedStationS[j] = 1;
            for (int k = 0; k < currentMinTimeB.length; k++) {
                if (checkedStationS[k] == 1) {
                    continue;
                }
                if (timeMatrix[j][k] != Long.MAX_VALUE) {
                    if (currentMinTimeB[k] > (currentMinTimeB[j] + timeMatrix[j][k])) {
                        currentMinTimeB[k] = currentMinTimeB[j] + timeMatrix[j][k];
                        previousStationC[k] = j;
                    }
                }
            }
        }

    }

    private int getMinTimeFromNoneCheckedStation() {
        int tempMinStation = Arrays.binarySearch(checkedStationS, 0);
        long tempMinTime = currentMinTimeB[tempMinStation];
        for (int i = 0; i < currentMinTimeB.length; i++) {
            if (checkedStationS[i] == 1) {
                continue;
            }
            if (currentMinTimeB[i] < tempMinTime) {
                tempMinStation = i;
                tempMinTime = currentMinTimeB[tempMinStation];
            }
        }
        return tempMinStation;
    }


    private void logResult() {
        AppLog.info(experimentHistory.getLoggerName(), "Request for min time between ");
        AppLog.info(experimentHistory.getLoggerName(), "id - " + idStartGenerator + " name - "
                + getGeneratorById(generatorList, idStartGenerator).getGeneratorName());
        AppLog.info(experimentHistory.getLoggerName(), "id - " + idEndGenerator + " name - "
                + getGeneratorById(generatorList, idEndGenerator).getGeneratorName());
        AppLog.info(experimentHistory.getLoggerName(), "**************************************************** ");
        AppLog.info(experimentHistory.getLoggerName(), "Result ");
        AppLog.info(experimentHistory.getLoggerName(), "Time - " + currentMinTimeB[reflectMap.get(idEndGenerator)]);
        AppLog.info(experimentHistory.getLoggerName(), "Reverse trip :");
        AppLog.info(experimentHistory.getLoggerName(), "id - " + idEndGenerator + " name - "
                + getGeneratorById(generatorList, idEndGenerator).getGeneratorName());
        int z = previousStationC[reflectMap.get(idEndGenerator)];
        while (z != -1) {
            AppLog.info(experimentHistory.getLoggerName(), "id - " + reverseReflectMap.get(z) + " name - "
                    + getGeneratorById(generatorList, reverseReflectMap.get(z)).getGeneratorName());
            z = previousStationC[z];
        }
    }

    protected Generator getGeneratorById(List<Generator> generatorList, int idGenerator) {
        for (Generator loopGenerator : generatorList) {
            if (loopGenerator.getIdGenerator() == idGenerator) {
                return loopGenerator;
            }
        }
        AppLog.info(experimentHistory.getLoggerName(), "Error. Generator with id - " + idGenerator +
                " don't exist in this experiment");
        throw new RuntimeException("Error. Generator with id - " + idGenerator +
                " don't exist in this experiment");
    }


//    �������� ��������
//
//
//    �������o, ��� ��� ���� ��������������. ����� ���������� ��������� ������� 1->i ��� ���� i=1..n �� ����� O(n2).
//
//    � �������� ������ ��������� ��������� ������ ����� ����������� (� ������ - ������ ����� 1, � ����� - ���).
//
//    ��� ����:
//    ��� ������� ����������� ������ i �������� ���������� ��������� ���� 1->i;
//    ��� ���� ��������, ��� ������� ����������� �� ����, ���������� ������ ����� ���������� ������;
//
//    ��� ������� ������������� ������ i �������� ���������� ��������� ���� 1->i,
//    � ������� � �������� ������������� ������������ ������ ���������� ������.
//
//    ��������� ���������� ������� ����������� �� ��������� ���������� ���������:
//    ���� ����� ���� ������������ ������� ����� ���, ��� �������� �������� ����� ����������,
//    �� ��� ����� �������� �������� ���������� ����������. � ����� ����, ����� ���� ����� �������� ����.
//    ���������� ������ ������������ ����� �� ���� ���� - ��� �� ���� ���� �������!
//    (����� ����������� ����������������� ���.)
//
//    ������� ��������� ����� � ����������, �� ������ ��������������� ����������, �������� ��� ������������ �������.
//    ��� ���� ���������� ������ ���� ����, � ������� ����� ����� �������� ��������� ������� ���������,
//    � ��� ����� �������, ��� ��� ����������� ��������� ������� � ����� ����� �� ��� �����.
//
//    ��� ����� ������������� ������� �������� ��������� ���������� ������� (� ��������� �������)
//    ���������� ������ ������ � ����� ���������� ������� ������� O(n).
//
//    ����� ��������� ��������
//
//
//    �������� ���������� ��� ������� �� N (= ����� ������ ����) ����� ������.
//    ������ ������ S �������� ����� � ����� ��������: 0 (������� ��� �� �����������) � 1 (������� ��� �����������);
//    ������ ������ B �������� ���������� - ������� ���������� ���������� �� �� ��������������� �������;
//    ������ ������ � �������� ������ ������ - k-� ������� �[k] ���� ����� ������������� �������
//    �� ������� ���������� ���� �� Vi � Vk. ������� ���������� A[i,k] ������ ����� ���� A[i,k];
//    ���� ����� ���� ���, �� A[i,k] ������������� ������� ����� �, ������ "�������� �������������".
//
//            1 (�������������). � ����� �� 1 �� N ��������� ������ ������
//            S; ��������� ������ i ������ C; ��������� i-� ������ �������
//    A � ������ B,
//    S[i]:=1; C[i]:=0 (i - ����� ��������� �������)
//            2 (����� ���). H���� ������� ����� ������������ (�. �. ��� k, ���
//            ������� S[k]=0); ����� ������� ����������� �� ������� j, �. �. B[j]<=B[k]
//    ����� ����������� ��������� ��������:
//    S[j]:=1;
//    ���� B[k] > B[j]+A[j,k], �� (B[k]:=B[j]+A[j,k]; C[k]:=j)
//    (������� ��������, ��� ���� Vi ... Vk �������, ��� ���� Vi...Vj Vk).
//            (���� ��� S[k] ��������, �� ����� ���� �� Vi �� Vk ����� B[k]. ������
//    ����) ����������� �������, �������� � ���������� ����).
//            3 (������ ������). (���� �� Vi �� Vk �������� � �������� �������
//    ��������� ����������:)
//            3.1.  z:=C[k];
//    3.2.  ������ z;
//    3.3.  z:=C[z]. ���� z = �, �� �����,
//    ����� ������� � 3.2.


}
