/**
 * springMVC controller
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.george_nika.simulation.controller.light_ajax_info.generator.LightGeneratorHistory;
import ua.george_nika.simulation.controller.light_ajax_info.experiment.LightOneRunningExperiment;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.GeneratorFactory;
import ua.george_nika.simulation.model.generator.GeneratorHistory;
import ua.george_nika.simulation.model.generator.RelatedGeneratorData;
import ua.george_nika.simulation.service.UserService;
import ua.george_nika.simulation.service.generator.GeneratorHistoryService;
import ua.george_nika.simulation.util.RunningExperimentHolder;
import ua.george_nika.simulation.service.generator.GeneratorService;
import ua.george_nika.simulation.service.generator.RelatedGeneratorDataService;
import ua.george_nika.simulation.controller.ControllerFactory;
import ua.george_nika.simulation.controller.form.GeneratorForm;
import ua.george_nika.simulation.controller.light_ajax_info.generator.LightGenerator;
import ua.george_nika.simulation.controller.light_ajax_info.generator.LightRelationGeneratorData;
import ua.george_nika.simulation.util.AppLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")

@Controller
public class GeneratorController {
    private static String LOGGER_NAME = AppLog.CONTROLLER;

    @Autowired
    GeneratorService generatorService;
    @Autowired
    GeneratorHistoryService generatorHistoryService;
    @Autowired
    UserService userService;
    @Autowired
    RelatedGeneratorDataService relatedGeneratorDataService;

    @RequestMapping("/generatorMainSetupPage")
    public String generatorMainSetupPage(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Go to generator main setup page");
        return "generator/generatorMainSetupPage";
    }

    @RequestMapping("/generatorMainHistoryPage")
    public String generatorMainHistoryPage(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Go to generator main history page");
        return "generator/generatorMainHistoryPage";
    }

    @RequestMapping("/generatorPage/{idGenerator}")
    public String generatorPage(HttpServletRequest request, HttpSession session, Model model,
                                @PathVariable("idGenerator") int idGenerator) {

        AppLog.userInfo(LOGGER_NAME, session, "Go to generator page id - " + idGenerator);
        Generator tempG = generatorService.getGeneratorById(idGenerator);
        GeneratorExtraController generatorExtraController =
                ControllerFactory.getGeneratorExtraControllerByType(tempG.getGeneratorType());
        model.addAttribute("generatorMainForm", new GeneratorForm(tempG));
        model.addAttribute("generator", tempG);
        generatorExtraController.addExtraDataToSetupPage(request, session, model, tempG);
        String resultJSPPage = generatorExtraController.getGeneratorSetupJSPPage();
        return "generator/" + resultJSPPage;
    }

    @RequestMapping("/generatorRunPage/{idExperimentHistory}/{idGeneratorHistory}")
    public String generatorRunPage(HttpServletRequest request, HttpSession session, Model model,
                                   @PathVariable("idExperimentHistory") int idExperimentHistory,
                                   @PathVariable("idGeneratorHistory") int idGeneratorHistory) {

        AppLog.userInfo(LOGGER_NAME, session, "Go to generator run page with history id - " + idGeneratorHistory
                + " for experiment with history id - " + idExperimentHistory);
        Generator generator = RunningExperimentHolder.getRunningGenerator(idExperimentHistory, idGeneratorHistory);
        model.addAttribute("generatorHistory", generator.getGeneratorHistory());
        GeneratorExtraController generatorExtraController =
                ControllerFactory.getGeneratorExtraControllerByType(generator.getGeneratorType());
        generatorExtraController.addExtraDataToRunPage(request, session, model, generator);
        String resultJSPPage = generatorExtraController.getGeneratorRunJSPPage();
        return "generator/" + resultJSPPage;
    }

    @RequestMapping("/generatorHistoryPage/{idGeneratorHistory}")
    public String generatorHistoryPage(HttpServletRequest request, HttpSession session, Model model,
                                       @PathVariable("idGeneratorHistory") int idGeneratorHistory) {

        AppLog.userInfo(LOGGER_NAME, session, "Go to generator history page id history - " + idGeneratorHistory);
        GeneratorHistory tempGH = generatorHistoryService.getGeneratorHistoryById(idGeneratorHistory);
        GeneratorExtraController generatorExtraController =
                ControllerFactory.getGeneratorExtraControllerByType(tempGH.getGeneratorType());
        model.addAttribute("generatorHistory", tempGH);
        generatorExtraController.addExtraDataToHistoryPage(request, session, model, tempGH);
        String resultJSPPage = generatorExtraController.getGeneratorHistoryJSPPage();
        return "generator/" + resultJSPPage;
    }

    @RequestMapping("/ajax/getGeneratorTypeList")
    @ResponseBody
    public List<String> getGeneratorTypeList(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Get generator type list");
        List<String> resultGeneratorTypeList = new ArrayList<>();
        resultGeneratorTypeList.addAll(GeneratorFactory.getAllRegisteredGeneratorType());
        return resultGeneratorTypeList;
    }

    @RequestMapping("/ajax/getGeneratorList")
    @ResponseBody
    public List<LightGenerator> getGeneratorList(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Get generator list");
        List<Generator> tempGeneratorList = generatorService.getAllLazyGenerator();
        List<LightGenerator> resultLightGeneratorList = new ArrayList<>();
        for (Generator loopGenerator : tempGeneratorList) {
            resultLightGeneratorList.add(new LightGenerator(loopGenerator));
        }
        return resultLightGeneratorList;
    }

    @RequestMapping("/ajax/getGeneratorHistoryList")
    @ResponseBody
    public List<LightGeneratorHistory> getGeneratorHistoryList(HttpServletRequest request, HttpSession session,
                                                               Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Get generator history list");
        List<GeneratorHistory> tempGeneratorHistoryList = generatorHistoryService.getAllLazyGeneratorHistory();
        List<LightGeneratorHistory> resultLightGeneratorHistoryList = new ArrayList<>();
        for (GeneratorHistory loopGeneratorHistory : tempGeneratorHistoryList) {
            resultLightGeneratorHistoryList.add(new LightGeneratorHistory(loopGeneratorHistory));
        }
        return resultLightGeneratorHistoryList;
    }

    @RequestMapping("/ajax/getGeneratorHistoryListByExperiment")
    @ResponseBody
    public List<LightGeneratorHistory> getGeneratorHistoryListByExperiment(
            HttpServletRequest request, HttpSession session, Model model,
            @RequestParam(value = "idExperimentHistory") int idExperimentHistory) {

        AppLog.userInfo(LOGGER_NAME, session, "Get generator history list by experiment history id - "
                + idExperimentHistory);
        List<GeneratorHistory> tempGeneratorHistoryList
                = generatorHistoryService.getAllLazyGeneratorHistoryByExperiment(idExperimentHistory);
        List<LightGeneratorHistory> resultLightGeneratorHistoryList = new ArrayList<>();
        for (GeneratorHistory loopGeneratorHistory : tempGeneratorHistoryList) {
            resultLightGeneratorHistoryList.add(new LightGeneratorHistory(loopGeneratorHistory));
        }
        return resultLightGeneratorHistoryList;
    }

    @RequestMapping("/ajax/getRelatedGeneratorDataList")
    @ResponseBody
    public List<LightRelationGeneratorData> getRelatedGeneratorList(
            HttpServletRequest request, HttpSession session, Model model,
            @RequestParam(value = "idGenerator") int idGenerator) {

        AppLog.userInfo(LOGGER_NAME, session, "Get related generator list id - " + idGenerator);
        List<RelatedGeneratorData> tempList = relatedGeneratorDataService.getAllRelatedGeneratorDataById(idGenerator);

        List<LightRelationGeneratorData> resultLightRGDataList = new ArrayList<>();
        for (RelatedGeneratorData loopData : tempList) {
            resultLightRGDataList.add(new LightRelationGeneratorData(loopData));
        }
        return resultLightRGDataList;
    }

    @RequestMapping("/generatorRelationSetupPage/{idGenerator}")
    public String generatorRelationSetupPage(
            HttpServletRequest request, HttpSession session, Model model,
            @PathVariable("idGenerator") int idGenerator) {

        AppLog.userInfo(LOGGER_NAME, session, "Go to generator relation setup page id - " + idGenerator);
        Generator tempG = generatorService.getLazyGeneratorById(idGenerator);
        model.addAttribute("generator", tempG);
        return "generator/generatorRelationSetupPage";
    }

    @RequestMapping("/editGeneratorAction/{idGenerator}")
    public String editGeneratorAction(
            HttpServletRequest request, HttpSession session, Model model,
            @PathVariable("idGenerator") int idGenerator,
            @ModelAttribute("generatorMainForm") GeneratorForm generatorMainForm) {

        AppLog.userInfo(LOGGER_NAME, session, "Edit generator id - " + idGenerator);
        userService.checkPermission(session);
        Generator tempGenerator = generatorService.getLazyGeneratorById(idGenerator);
        generatorMainForm.updateGenerator(tempGenerator);
        generatorService.updateLazyGenerator(tempGenerator);
        return generatorPage(request, session, model, idGenerator);
    }

    @RequestMapping("ajax/createGenerator")
    @ResponseBody
    public boolean createGenerator(HttpServletRequest request, HttpSession session, Model model,
                                   @RequestParam(value = "generatorType") String generatorType) {
        AppLog.userInfo(LOGGER_NAME, session, "Create generator");
        userService.checkPermission(session);
        generatorService.createGeneratorByType(generatorType);
        return true;
    }

    @RequestMapping("ajax/deleteGenerator")
    @ResponseBody
    public boolean deleteGenerator(HttpServletRequest request, HttpSession session, Model model,
                                   @RequestParam(value = "idGenerator") int idGenerator) {
        AppLog.userInfo(LOGGER_NAME, session, "Delete generator id - " + idGenerator);
        userService.checkPermission(session);
        generatorService.deleteGeneratorById(idGenerator);
        return true;
    }

    @RequestMapping("/ajax/getGeneratorRunList")
    @ResponseBody
    public LightOneRunningExperiment getGeneratorRunList(
            HttpServletRequest request, HttpSession session, Model model,
            @RequestParam(value = "idExperimentHistory") int idExperimentHistory) {

        Experiment experiment = RunningExperimentHolder.getRunningExperiment(idExperimentHistory);
        return new LightOneRunningExperiment(experiment);
    }


}
