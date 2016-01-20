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
import ua.george_nika.simulation.controller.form.TimeExperimentExtraDataForm;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.model.experiment.impl.TimeExperiment;
import ua.george_nika.simulation.service.UserService;
import ua.george_nika.simulation.service.experiment.ExperimentService;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

@Controller
public class TimeExperimentController implements ExperimentExtraController {
    public static final String EXPERIMENT_SETUP_JSP_PAGE = "timeExperimentSetupPage";
    public static final String EXPERIMENT_RUN_JSP_PAGE = "experimentCommonRunPage";
    public static final String EXPERIMENT_HISTORY_JSP_PAGE = "timeExperimentHistoryPage";

    private static String experimentType = TimeExperiment.EXPERIMENT_TYPE;
    private static String LOGGER_NAME = AppLog.CONTROLLER;

    @Autowired
    ExperimentService experimentService;
    @Autowired
    UserService userService;
    @Autowired
    ExperimentController experimentController;

    static {
        ControllerFactory.registerExperimentControllerClassInFactory(experimentType,
                TimeExperimentController.class.getCanonicalName());
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
        TimeExperiment timeExperiment = ClassTypeUtil.getCheckedClass(experiment, TimeExperiment.class);
        model.addAttribute("timeExperimentExtraDataForm", new TimeExperimentExtraDataForm(timeExperiment));
    }

    @Override
    public void addExtraDataToRunPage(HttpServletRequest request, HttpSession session, Model model,
                                      Experiment experiment) {
        //do nothing
    }

    @Override
    public void addExtraDataToHistoryPage(HttpServletRequest request, HttpSession session, Model model,
                                          ExperimentHistory experimentHistory) {
        //do nothing
    }

    @RequestMapping("/editTimeExperimentExtraDataAction/{idExperiment}")
    public String editTimeExperimentExtraDataAction
            (HttpServletRequest request, HttpSession session, Model model,
             @PathVariable("idExperiment") int idExperiment,
             @ModelAttribute("timeExperimentExtraDataForm") TimeExperimentExtraDataForm timeExperimentExtraDataForm) {

        AppLog.userInfo(LOGGER_NAME, session, "Edit time experiment extra data for experiment id - " + idExperiment);
        userService.checkPermission(session);
        Experiment tempExp = experimentService.getExperimentById(idExperiment);
        TimeExperiment timeExperiment = ClassTypeUtil.getCheckedClass(tempExp, TimeExperiment.class);
        timeExperimentExtraDataForm.updateExtraDataExperiment(timeExperiment);
        experimentService.updateExperimentExtraData(timeExperiment);
        return experimentController.experimentSetupPage(request, session, model, idExperiment);
    }
}
