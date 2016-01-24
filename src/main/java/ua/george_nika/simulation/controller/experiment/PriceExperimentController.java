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
import ua.george_nika.simulation.controller.ControllerFactory;
import ua.george_nika.simulation.controller.form.PriceExperimentExtraDataForm;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.model.experiment.impl.PriceExperiment;
import ua.george_nika.simulation.service.UserService;
import ua.george_nika.simulation.service.experiment.ExperimentService;
import ua.george_nika.simulation.util.AppLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

@Controller
public class PriceExperimentController implements ExperimentExtraController {
    public static final String EXPERIMENT_SETUP_JSP_PAGE = "priceExperimentSetupPage";
    public static final String EXPERIMENT_RUN_JSP_PAGE = "experimentCommonRunPage";
    public static final String EXPERIMENT_HISTORY_JSP_PAGE = "experimentCommonHistoryPage";

    private static final String experimentType = PriceExperiment.EXPERIMENT_TYPE;
    private static String LOGGER_NAME = AppLog.CONTROLLER;

    @Autowired
    ExperimentService experimentService;
    @Autowired
    UserService userService;
    @Autowired
    ExperimentController experimentController;

    static {
        ControllerFactory.registerExperimentControllerClassInFactory(experimentType,
                PriceExperimentController.class.getCanonicalName());
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
        model.addAttribute("priceExperimentExtraDataForm",
                new PriceExperimentExtraDataForm((PriceExperiment) experiment));
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

    @RequestMapping("/editPriceExperimentExtraDataAction/{idExperiment}")
    public String editPriceExperimentExtraDataAction
            (HttpServletRequest request, HttpSession session, Model model,
             @PathVariable("idExperiment") int idExperiment,
             @ModelAttribute
                     ("priceExperimentExtraDataForm") PriceExperimentExtraDataForm priceExperimentExtraDataForm) {

        AppLog.userInfo(LOGGER_NAME, session, "Edit price experiment extra data for experiment id - " + idExperiment);
        userService.checkPermission(session);
        Experiment tempExperiment = experimentService.getExperimentById(idExperiment);
        priceExperimentExtraDataForm.updateExtraDataExperiment((PriceExperiment) tempExperiment);

        experimentService.updateExperimentExtraData(tempExperiment);
        return experimentController.experimentSetupPage(request, session, model, idExperiment);
    }
}
