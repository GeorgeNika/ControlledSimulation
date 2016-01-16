package ua.george_nika.simulation.controller.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.impl.StationGenerator;
import ua.george_nika.simulation.model.generator.impl.HumanAppearInfo;
import ua.george_nika.simulation.service.UserService;
import ua.george_nika.simulation.service.generator.StationService;
import ua.george_nika.simulation.service.generator.GeneratorService;
import ua.george_nika.simulation.controller.ControllerFactory;
import ua.george_nika.simulation.controller.light_ajax_info.LightHumanAppearInfo;
import ua.george_nika.simulation.util.AppLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by george on 16.12.2015.
 */
@Controller
public class StationGeneratorController implements GeneratorExtraController {
    private static String LOGGER_NAME = AppLog.CONTROLLER;

    public static final String GENERATOR_JSP_PAGE = "stationGeneratorSetupPage";
    private static String generatorType = StationGenerator.GENERATOR_TYPE;

    @Autowired
    GeneratorService generatorService;

    @Autowired
    UserService userService;

    @Autowired
    StationService stationService;

    static {
        ControllerFactory.registerGeneratorControllerClassInFactory(generatorType,
                StationGeneratorController.class.getCanonicalName());
    }

    @Override
    public String getGeneratorJSPPage() {
        return GENERATOR_JSP_PAGE;
    }

    @Override
    public void addExtraData(HttpServletRequest request, HttpSession session, Model model, Generator generator) {
        // nothing to add
    }

    @RequestMapping("/humanAppearInfoSetupPage/{idGenerator}")
    public String humanAppearInfoSetupPage(HttpServletRequest request,
                                           HttpSession session,
                                           Model model,
                                           @PathVariable("idGenerator") int idGenerator) {
        AppLog.userInfo(LOGGER_NAME, session, "Go to human appear info setup page for generator id - " + idGenerator);
        Generator generator = generatorService.getGeneratorById(idGenerator);
        model.addAttribute("generator", generator);
        return "generator/humanAppearInfoSetupPage";
    }


    @RequestMapping("ajax/getHumanAppearInfoList")
    @ResponseBody
    public List<LightHumanAppearInfo> getHumanAppearInfoList(HttpServletRequest request,
                                                             HttpSession session,
                                                             Model model,
                                                             @RequestParam(value = "idGenerator") int idGenerator) {
        AppLog.userInfo(LOGGER_NAME, session, "Get human appear info list for generator id - " + idGenerator);
        List<LightHumanAppearInfo> resultInfoList = new ArrayList<>();
        for (HumanAppearInfo loopInfo : stationService.getHumanAppearInfoList(idGenerator)) {
            resultInfoList.add(new LightHumanAppearInfo(loopInfo));
        }
        Collections.sort(resultInfoList, new Comparator<LightHumanAppearInfo>() {
            @Override
            public int compare(LightHumanAppearInfo o1, LightHumanAppearInfo o2) {
                return Integer.compare(o1.getStartTimeMs(),o2.getStartTimeMs());
            }
        });
        return resultInfoList;
    }

    @RequestMapping("ajax/createHumanAppearInfo")
    @ResponseBody
    public boolean createHumanAppearInfo(HttpServletRequest request,
                                         HttpSession session,
                                         Model model,
                                         @RequestParam(value = "idGenerator") int idGenerator) {
        AppLog.userInfo(LOGGER_NAME, session, "Create new human appear info for generator id - " + idGenerator);
        userService.checkPermission(session);
        stationService.createNewHumanAppearInfo(idGenerator);
        return true;
    }

    @RequestMapping("ajax/deleteHumanAppearInfo")
    @ResponseBody
    public boolean deleteHumanAppearInfo(HttpServletRequest request,
                                         HttpSession session,
                                         Model model,
                                         @RequestParam(value = "idHumanAppearInfo") int idHumanAppearInfo) {
        AppLog.userInfo(LOGGER_NAME, session, "Delete human appear info id - " + idHumanAppearInfo);
        userService.checkPermission(session);
        stationService.deleteHumanAppearInfo(idHumanAppearInfo);
        return true;
    }

    @RequestMapping("ajax/getHumanAppearInfo")
    @ResponseBody
    public LightHumanAppearInfo getHumanAppearInfo(HttpServletRequest request, HttpSession session, Model model,
                                                   @RequestParam(value = "idGenerator") int idGenerator,
                                                   @RequestParam(value = "idHumanAppearInfo") int idHumanAppearInfo) {
        AppLog.userInfo(LOGGER_NAME, session, "Get human appear info id - " + idHumanAppearInfo +
                " for generator id - " + idGenerator);
        HumanAppearInfo tempHAInfo = stationService.getHumanAppearInfo(idGenerator, idHumanAppearInfo);
        return new LightHumanAppearInfo(tempHAInfo);
    }


    @RequestMapping("ajax/editHumanAppearInfo")
    @ResponseBody
    public boolean editHumanAppearInfo(HttpServletRequest request, HttpSession session, Model model,
                                       @RequestParam(value = "idGenerator") int idGenerator,
                                       @RequestParam(value = "idHumanAppearInfo") int idHumanAppearInfo,
                                       @RequestParam(value = "startTimeMs") int startTimeMs,
                                       @RequestParam(value = "endTimeMs") int endTimeMs,
                                       @RequestParam(value = "percent") double percent) {
        AppLog.userInfo(LOGGER_NAME, session, "Edit human appear info id - " + idHumanAppearInfo +
                " for generator id - " + idGenerator);
        userService.checkPermission(session);
        HumanAppearInfo humanAppearInfo = stationService.getHumanAppearInfo(idGenerator, idHumanAppearInfo);

        humanAppearInfo.setStartTimeMs(startTimeMs);
        humanAppearInfo.setEndTimeMs(endTimeMs);
        humanAppearInfo.setPercent(percent);

        stationService.updateHumanAppearInfo(humanAppearInfo);
        return true;
    }
}
