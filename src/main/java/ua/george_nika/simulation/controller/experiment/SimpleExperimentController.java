/**
 * springMVC controller
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.experiment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.model.experiment.impl.SimpleExperiment;
import ua.george_nika.simulation.service.UserService;
import ua.george_nika.simulation.service.experiment.ExperimentService;
import ua.george_nika.simulation.controller.ControllerFactory;
import ua.george_nika.simulation.controller.form.SimpleExperimentExtraDataForm;
import ua.george_nika.simulation.util.AppLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

@Controller
public class SimpleExperimentController implements ExperimentExtraController {
    public static final String EXPERIMENT_SETUP_JSP_PAGE = "simpleExperimentSetupPage";
    public static final String EXPERIMENT_RUN_JSP_PAGE = "experimentCommonRunPage";
    public static final String EXPERIMENT_HISTORY_JSP_PAGE = "simpleExperimentHistoryPage";

    private static String experimentType = SimpleExperiment.EXPERIMENT_TYPE;
    private static String LOGGER_NAME = AppLog.CONTROLLER;

    @Autowired
    ExperimentService experimentService;
    @Autowired
    UserService userService;
    @Autowired
    ExperimentController experimentController;

    static {
        ControllerFactory.registerExperimentControllerClassInFactory(experimentType,
                SimpleExperimentController.class.getCanonicalName());
    }

    @Override
    public String getExperimentSetupJSPPage() {
        return EXPERIMENT_SETUP_JSP_PAGE;
    }

    @Override
    public String getExperimentRunJSPPage() {
        return EXPERIMENT_RUN_JSP_PAGE;
    }

    @Override
    public String getExperimentHistoryJSPPage() {
        return EXPERIMENT_HISTORY_JSP_PAGE;
    }

    @Override
    public void addExtraDataToSetupPage(HttpServletRequest request, HttpSession session, Model model,
                                        Experiment experiment) {
        model.addAttribute("simpleExperimentExtraDataForm",
                new SimpleExperimentExtraDataForm((SimpleExperiment) experiment));
    }

    @Override
    public void addExtraDataToRunPage(HttpServletRequest request, HttpSession session, Model model,
                                      Experiment experiment) {
        // do nothing
    }

    @Override
    public void addExtraDataToHistoryPage(HttpServletRequest request, HttpSession session, Model model,
                                          ExperimentHistory experimentHistory) {
        // do nothing
    }

    @RequestMapping("/editSimpleExperimentExtraDataAction/{idExperiment}")
    public String editSimpleExperimentExtraDataAction
            (HttpServletRequest request, HttpSession session, Model model,
             @PathVariable("idExperiment") int idExperiment,
             @ModelAttribute
                     ("simpleExperimentExtraDataForm") SimpleExperimentExtraDataForm simpleExperimentExtraDataForm) {
        AppLog.userInfo(LOGGER_NAME, session, "Edit simple experiment extra data for experiment id - " + idExperiment);
        userService.checkPermission(session);
        Experiment tempExperiment = experimentService.getExperimentById(idExperiment);
        simpleExperimentExtraDataForm.updateExtraDataExperiment((SimpleExperiment) tempExperiment);

        experimentService.updateExperimentExtraData(tempExperiment);
        return experimentController.experimentSetupPage(request, session, model, idExperiment);
    }
}
