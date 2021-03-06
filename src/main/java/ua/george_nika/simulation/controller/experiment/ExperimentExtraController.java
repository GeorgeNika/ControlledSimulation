/**
 * interface for extra controllers
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.experiment;

import org.springframework.ui.Model;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface ExperimentExtraController {

    String getExperimentSetupJSPPage();

    String getExperimentRunJSPPage();

    String getExperimentHistoryJSPPage();

    void addExtraDataToSetupPage(HttpServletRequest request, HttpSession session, Model model, Experiment experiment);

    void addExtraDataToRunPage(HttpServletRequest request, HttpSession session, Model model, Experiment experiment);

    void addExtraDataToHistoryPage(HttpServletRequest request, HttpSession session, Model model,
                                   ExperimentHistory experimentHistory);
}
