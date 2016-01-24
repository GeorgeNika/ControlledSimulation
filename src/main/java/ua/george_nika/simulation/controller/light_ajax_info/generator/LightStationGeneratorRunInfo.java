/**
 * used for send request when get ajax response
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.light_ajax_info.generator;

import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.impl.StationGeneratorHistory;
import ua.george_nika.simulation.util.ClassTypeUtil;
import ua.george_nika.simulation.util.TimeToStringUtil;

@SuppressWarnings("unused")

public class LightStationGeneratorRunInfo {

    private String currentState;
    private String globalState;
    private String currentDateTime;
    private boolean paused;

    public LightStationGeneratorRunInfo(Experiment experiment, Generator generator) {
        this.currentDateTime = TimeToStringUtil.getSafeString(experiment.getCurrentTime());
        this.paused = experiment.getPaused().get();
        this.currentState = generator.getEntityType() + " count - " + generator.getDependentEntityList().size();
        StationGeneratorHistory stationGeneratorHistory
                = ClassTypeUtil.getCheckedClass(generator.getGeneratorHistory(), StationGeneratorHistory.class);
        this.globalState = "create - " + stationGeneratorHistory.getCreateEntity()
                + " send - " + stationGeneratorHistory.getSendEntity()
                + " receive - " + stationGeneratorHistory.getReceiveEntity()
                + " destroy - " + stationGeneratorHistory.getDestroyEntity();
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getGlobalState() {
        return globalState;
    }

    public void setGlobalState(String globalState) {
        this.globalState = globalState;
    }

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
