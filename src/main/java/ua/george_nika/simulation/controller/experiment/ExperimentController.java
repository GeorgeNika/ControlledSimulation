package ua.george_nika.simulation.controller.experiment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.george_nika.simulation.controller.light_ajax_info.LightExperimentHistory;
import ua.george_nika.simulation.controller.light_ajax_info.LightRunningExperiment;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentFactory;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.service.UserService;
import ua.george_nika.simulation.service.experiment.ExperimentHistoryService;
import ua.george_nika.simulation.service.experiment.ExperimentService;
import ua.george_nika.simulation.service.generator.GeneratorService;
import ua.george_nika.simulation.controller.ControllerFactory;
import ua.george_nika.simulation.controller.form.ExperimentForm;
import ua.george_nika.simulation.controller.light_ajax_info.LightExperiment;
import ua.george_nika.simulation.controller.light_ajax_info.LightGenerator;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.RunningExperimentHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by george on 22.12.2015.
 */
@Controller
public class ExperimentController {
    private static String LOGGER_NAME = AppLog.CONTROLLER;

    @Autowired
    ExperimentService experimentService;

    @Autowired
    ExperimentHistoryService experimentHistoryService;

    @Autowired
    GeneratorService generatorService;

    @Autowired
    UserService userService;


    @RequestMapping("/experimentMainSetupPage")
    public String experimentMainSetupPage(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Go to experiment main setup page");
        return "experiment/experimentMainSetupPage";
    }

    @RequestMapping("/experimentMainHistoryPage")
    public String experimentMainHistoryPage(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Go to experiment main history page");
        return "experiment/experimentMainHistoryPage";
    }

    @RequestMapping("/generatorListForExperiment/{idExperiment}")
    public String generatorListForExperiment(HttpServletRequest request, HttpSession session, Model model,
                                             @PathVariable("idExperiment") int idExperiment) {
        AppLog.userInfo(LOGGER_NAME, session, "Go to generator list page for experiment id - " + idExperiment);
        model.addAttribute("idExperiment", idExperiment);
        return "experiment/generatorListForExperiment";
    }

    @RequestMapping("/experimentSetupPage/{idExperiment}")
    public String experimentSetupPage(HttpServletRequest request, HttpSession session, Model model,
                                      @PathVariable("idExperiment") int idExperiment) {

        AppLog.userInfo(LOGGER_NAME, session, "Go to experiment setup page id - " + idExperiment);
        Experiment tempE = experimentService.getExperimentById(idExperiment);
        ExperimentExtraController experimentExtraController =
                ControllerFactory.getExperimentExtraControllerByType(tempE.getExperimentType());
        model.addAttribute("experimentMainForm", new ExperimentForm(tempE));
        model.addAttribute("experiment", tempE);
        experimentExtraController.addExtraDataToSetupPage(request, session, model, tempE);
        String resultJSPPage = experimentExtraController.getExperimentSetupJSPPage();
        return "experiment/" + resultJSPPage;
    }

    @RequestMapping("/experimentHistoryPage/{idExperimentHistory}")
    public String experimentHistoryPage(HttpServletRequest request, HttpSession session, Model model,
                                        @PathVariable("idExperimentHistory") int idExperimentHistory) {

        AppLog.userInfo(LOGGER_NAME, session, "Go to experiment history page id history - " + idExperimentHistory);
        ExperimentHistory tempEH = experimentHistoryService.getExperimentHistoryById(idExperimentHistory);
        ExperimentExtraController experimentExtraController =
                ControllerFactory.getExperimentExtraControllerByType(tempEH.getExperimentType());
        model.addAttribute("experimentHistory", tempEH);
        experimentExtraController.addExtraDataToHistoryPage(request, session, model, tempEH);
        String resultJSPPage = experimentExtraController.getExperimentHistoryJSPPage();
        return "experiment/" + resultJSPPage;
    }

    @RequestMapping("/ajax/getExperimentList")
    @ResponseBody
    public List<LightExperiment> getExperimentList(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Get experiment list");
        List<Experiment> tempExperimentList = experimentService.getAllLazyExperiment();
        List<LightExperiment> resultLightExperimentList = new ArrayList<LightExperiment>();
        for (Experiment loopExperiment : tempExperimentList) {
            resultLightExperimentList.add(new LightExperiment(loopExperiment));
        }
        return resultLightExperimentList;
    }

    @RequestMapping("/ajax/getExperimentHistoryList")
    @ResponseBody
    public List<LightExperimentHistory> getExperimentHistoryList(HttpServletRequest request, HttpSession session,
                                                                 Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Get experiment history list");
        List<ExperimentHistory> tempExperimentHistoryList = experimentHistoryService.getAllLazyExperimentHistory();
        List<LightExperimentHistory> resultLightExperimentHistoryList = new ArrayList<>();
        for (ExperimentHistory loopExperimentHistory : tempExperimentHistoryList) {
            resultLightExperimentHistoryList.add(new LightExperimentHistory(loopExperimentHistory));
        }
        return resultLightExperimentHistoryList;
    }

    @RequestMapping("/ajax/getExperimentTypeList")
    @ResponseBody
    public List<String> getExperimentTypeList(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Get experiment type list");
        List<String> resultExperimentTypeList = new ArrayList<>();
        resultExperimentTypeList.addAll(ExperimentFactory.getAllRegisteredExperimentType());
        return resultExperimentTypeList;
    }

    @RequestMapping("ajax/createExperiment")
    @ResponseBody
    public boolean createExperiment(HttpServletRequest request, HttpSession session, Model model,
                                    @RequestParam(value = "experimentType") String experimentType) {

        AppLog.userInfo(LOGGER_NAME, session, "Create new experiment");
        userService.checkPermission(session);
        experimentService.createExperimentByType(experimentType);
        return true;
    }

    @RequestMapping("/editExperimentAction/{idExperiment}")
    public String editExperimentAction(HttpServletRequest request,
                                       HttpSession session,
                                       Model model,
                                       @PathVariable("idExperiment") int idExperiment,
                                       @ModelAttribute("experimentMainForm") ExperimentForm experimentMainForm) {

        AppLog.userInfo(LOGGER_NAME, session, "Edit experiment id - " + idExperiment);
        userService.checkPermission(session);

        Experiment tempExperiment = experimentService.getLazyExperimentById(idExperiment);
        experimentMainForm.updateExperiment(tempExperiment);
        experimentService.updateLazyExperiment(tempExperiment);
        return experimentSetupPage(request, session, model, idExperiment);
    }

    @RequestMapping("ajax/deleteExperiment")
    @ResponseBody
    public boolean deleteExperiment(HttpServletRequest request, HttpSession session, Model model,
                                    @RequestParam(value = "idExperiment") int idExperiment) {

        AppLog.userInfo(LOGGER_NAME, session, "Delete experiment id - " + idExperiment);
        userService.checkPermission(session);
        experimentService.deleteExperimentById(idExperiment);
        return true;
    }

    @RequestMapping("ajax/changeExperimentType")
    @ResponseBody
    public boolean changeExperimentType(HttpServletRequest request, HttpSession session, Model model,
                                        @RequestParam(value = "idExperiment") int idExperiment,
                                        @RequestParam(value = "experimentType") String newExperimentType) {
        AppLog.userInfo(LOGGER_NAME, session, "Change experiment id - " + idExperiment +
                " type - " + newExperimentType);
        userService.checkPermission(session);
        experimentService.changeExperimentType(idExperiment, newExperimentType);
        return true;
    }

    @RequestMapping("/ajax/getGeneratorListForExperiment")
    @ResponseBody
    public List<LightGenerator> getGeneratorListForExperiment(HttpServletRequest request, HttpSession session,
                                                              Model model,
                                                              @RequestParam(value = "idExperiment") int idExperiment) {
        AppLog.userInfo(LOGGER_NAME, session, "Get generator list for experiment id - " + idExperiment);
        Experiment tempE = experimentService.getExperimentById(idExperiment);
        List<LightGenerator> resultLightGeneratorList = new ArrayList<LightGenerator>();
        for (Generator loopGenerator : tempE.getGeneratorList()) {
            resultLightGeneratorList.add(new LightGenerator(loopGenerator));
        }
        Collections.sort(resultLightGeneratorList, new Comparator<LightGenerator>() {
            @Override
            public int compare(LightGenerator o1, LightGenerator o2) {
                if (o1.getGeneratorType().equals(o2.getGeneratorType())) {
                    return o1.getGeneratorName().compareTo(o2.getGeneratorName());
                }
                return o1.getGeneratorType().compareTo(o2.getGeneratorType());
            }
        });
        return resultLightGeneratorList;
    }

    @RequestMapping("/ajax/getNonUsedGeneratorListForExperiment")
    @ResponseBody
    public List<LightGenerator> getNonUsedGeneratorListForExperiment
            (HttpServletRequest request, HttpSession session, Model model,
             @RequestParam(value = "idExperiment") int idExperiment) {

        AppLog.userInfo(LOGGER_NAME, session, "Get non used generator list for experiment id - " + idExperiment);
        userService.checkPermission(session);
        List<LightGenerator> resultLightGeneratorList = new ArrayList<LightGenerator>();
        Set<Integer> experimentGeneratorIdList = new HashSet<>();
        for (Generator loopG : generatorService.getAllGeneratorByExperimentId(idExperiment)) {
            experimentGeneratorIdList.add(loopG.getIdGenerator());
        }
        List<Generator> allGeneratorList = generatorService.getAllLazyGenerator();
        for (Generator loopGenerator : allGeneratorList) {
            if (!experimentGeneratorIdList.contains(loopGenerator.getIdGenerator())) {
                resultLightGeneratorList.add(new LightGenerator(loopGenerator));
            }
        }
        return resultLightGeneratorList;
    }


    @RequestMapping("/ajax/addGeneratorToExperimentList")
    @ResponseBody
    public boolean addGeneratorToExperimentList(HttpServletRequest request, HttpSession session,
                                                Model model,
                                                @RequestParam(value = "idExperiment") int idExperiment,
                                                @RequestParam(value = "idGenerator") int idGenerator) {
        AppLog.userInfo(LOGGER_NAME, session,
                "Add generator id - " + idGenerator + " to experiment list id - " + idExperiment);
        userService.checkPermission(session);
        experimentService.addGeneratorToExperimentList(idExperiment, idGenerator);
        return true;
    }

    @RequestMapping("/ajax/deleteGeneratorFromExperimentList")
    @ResponseBody
    public boolean deleteGeneratorFromExperimentList(HttpServletRequest request, HttpSession session,
                                                     Model model,
                                                     @RequestParam(value = "idExperiment") int idExperiment,
                                                     @RequestParam(value = "idGenerator") int idGenerator) {
        AppLog.userInfo(LOGGER_NAME, session,
                "Delete generator id - " + idGenerator + " from experiment list id - " + idExperiment);
        userService.checkPermission(session);
        experimentService.deleteGeneratorFromExperimentList(idExperiment, idGenerator);
        return true;
    }

    @RequestMapping("/experimentRunPage/{idExperimentHistory}")
    public String experimentRunPage(HttpServletRequest request, HttpSession session, Model model,
                                    @PathVariable("idExperimentHistory") int idExperimentHistory) {

        AppLog.userInfo(LOGGER_NAME, session, "Go to experiment run page with history id - " + idExperimentHistory);
        Experiment experiment = RunningExperimentHolder.getExperimentByExperimentHistoryId(idExperimentHistory);
        model.addAttribute("experimentHistory", experiment.getExperimentHistory());
        ExperimentExtraController experimentExtraController =
                ControllerFactory.getExperimentExtraControllerByType(experiment.getExperimentType());
        experimentExtraController.addExtraDataToRunPage(request, session, model, experiment);
        String resultJSPPage = experimentExtraController.getExperimentRunJSPPage();
        return "experiment/" + resultJSPPage;
    }

    @RequestMapping("/ajax/runExperiment")
    @ResponseBody
    public boolean runExperiment(HttpServletRequest request, HttpSession session, Model model,
                                 @RequestParam(value = "idExperiment") int idExperiment) {
        AppLog.userInfo(LOGGER_NAME, session, "Start experiment id - " + idExperiment);
        userService.checkPermission(session);
        Experiment experiment = experimentService.getExperimentById(idExperiment);
        experimentService.startExperiment(experiment);
        return true;
    }

    @RequestMapping("/ajax/getRunningExperimentList")
    @ResponseBody
    public List<LightRunningExperiment> getRunningExperimentList(HttpServletRequest request,
                                                                 HttpSession session, Model model) {

        //AppLog.userInfo(LOGGER_NAME, session,"Get running experiment list" );
        // it's disabled, because it's happened very often.

        List<LightRunningExperiment> resultList = new ArrayList<>();

        for (Experiment loopExp : RunningExperimentHolder.getRunningExperimentList()) {
            resultList.add(new LightRunningExperiment(loopExp));
        }

        return resultList;
    }

    @RequestMapping("/ajax/stopExperiment")
    @ResponseBody
    public boolean stopExperiment(HttpServletRequest request, HttpSession session, Model model,
                                  @RequestParam(value = "idExperimentHistory") int idExperimentHistory) {
        AppLog.userInfo(LOGGER_NAME, session, "Stop running experiment with history id - " + idExperimentHistory);
        userService.checkPermission(session);
        Experiment experiment = RunningExperimentHolder.getExperimentByExperimentHistoryId(idExperimentHistory);
        experimentService.stopExperiment(experiment);
        return true;
    }

    @RequestMapping("/ajax/pauseExperiment")
    @ResponseBody
    public boolean pauseExperiment(HttpServletRequest request, HttpSession session, Model model,
                                   @RequestParam(value = "idExperimentHistory") int idExperimentHistory) {
        AppLog.userInfo(LOGGER_NAME, session, "Pause running experiment with history id - " + idExperimentHistory);
        userService.checkPermission(session);
        Experiment experiment = RunningExperimentHolder.getExperimentByExperimentHistoryId(idExperimentHistory);
        experimentService.pauseExperiment(experiment);
        return true;
    }

    @RequestMapping("/ajax/continueExperiment")
    @ResponseBody
    public boolean continueExperiment(HttpServletRequest request, HttpSession session, Model model,
                                      @RequestParam(value = "idExperimentHistory") int idExperimentHistory) {
        AppLog.userInfo(LOGGER_NAME, session, "Continue running experiment with history id - " + idExperimentHistory);
        userService.checkPermission(session);
        Experiment experiment = RunningExperimentHolder.getExperimentByExperimentHistoryId(idExperimentHistory);
        experimentService.continueExperiment(experiment);
        return true;
    }

}
