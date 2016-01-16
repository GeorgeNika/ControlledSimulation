package ua.george_nika.simulation.controller.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.impl.BusStartInfo;
import ua.george_nika.simulation.model.generator.impl.RouteGenerator;
import ua.george_nika.simulation.service.UserService;
import ua.george_nika.simulation.service.generator.GeneratorService;
import ua.george_nika.simulation.service.generator.RouteService;
import ua.george_nika.simulation.controller.ControllerFactory;
import ua.george_nika.simulation.controller.light_ajax_info.LightBusStartInfo;
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
public class RouteGeneratorController implements GeneratorExtraController {
    private static String LOGGER_NAME = AppLog.CONTROLLER;

    public static final String GENERATOR_JSP_PAGE = "routeGeneratorSetupPage";
    private static String generatorType = RouteGenerator.GENERATOR_TYPE;

    @Autowired
    GeneratorService generatorService;

    @Autowired
    RouteService routeService;

    @Autowired
    UserService userService;

    static {
        ControllerFactory.registerGeneratorControllerClassInFactory(generatorType,
                RouteGeneratorController.class.getCanonicalName());
    }

    @Override
    public void addExtraData(HttpServletRequest request, HttpSession session, Model model, Generator generator) {
        // nothing to add
    }

    @Override
    public String getGeneratorJSPPage() {
        return GENERATOR_JSP_PAGE;
    }

    @RequestMapping("/busStartInfoSetupPage/{idGenerator}")
    public String busStartInfoSetupPage(HttpServletRequest request,
                                        HttpSession session,
                                        Model model,
                                        @PathVariable("idGenerator") int idGenerator) {
        AppLog.userInfo(LOGGER_NAME, session, "Go to bus start info setup page for generator id - " + idGenerator);
        Generator generator = generatorService.getGeneratorById(idGenerator);
        model.addAttribute("generator", generator);
        return "generator/busStartInfoSetupPage";
    }


    @RequestMapping("ajax/getBusStartInfoList")
    @ResponseBody
    public List<LightBusStartInfo> getBusStartInfoList(HttpServletRequest request,
                                                       HttpSession session,
                                                       Model model,
                                                       @RequestParam(value = "idGenerator") int idGenerator) {
        AppLog.userInfo(LOGGER_NAME, session, "Get bus start info list for generator id - " + idGenerator);

        List<LightBusStartInfo> resultInfoList = new ArrayList<>();
        for (BusStartInfo loopInfo : routeService.getBusStartInfoList(idGenerator)) {
            resultInfoList.add(new LightBusStartInfo(loopInfo));
        }
        Collections.sort(resultInfoList, new Comparator<LightBusStartInfo>() {
            @Override
            public int compare(LightBusStartInfo o1, LightBusStartInfo o2) {
                return Integer.compare(o1.getStartTimeMs(),o2.getStartTimeMs());
            }
        });
        return resultInfoList;
    }

    @RequestMapping("ajax/createBusStartInfo")
    @ResponseBody
    public boolean createBusStartInfo(HttpServletRequest request,
                                      HttpSession session,
                                      Model model,
                                      @RequestParam(value = "idGenerator") int idGenerator) {

        AppLog.userInfo(LOGGER_NAME, session, "Create new bus start info for generator id - " + idGenerator);
        userService.checkPermission(session);
        routeService.createNewBusStartInfo(idGenerator);
        return true;
    }

    @RequestMapping("ajax/deleteBusStartInfo")
    @ResponseBody
    public boolean deleteBusStartInfo(HttpServletRequest request,
                                      HttpSession session,
                                      Model model,
                                      @RequestParam(value = "idBusStartInfo") int idBusStartInfo) {
        AppLog.userInfo(LOGGER_NAME, session, "Delete bus start info id - " + idBusStartInfo);
        userService.checkPermission(session);
        routeService.deleteBusStartInfo(idBusStartInfo);
        return true;
    }

    @RequestMapping("ajax/getBusStartInfo")
    @ResponseBody
    public LightBusStartInfo getBusStartInfo(HttpServletRequest request, HttpSession session, Model model,
                                             @RequestParam(value = "idGenerator") int idGenerator,
                                             @RequestParam(value = "idBusStartInfo") int idBusStartInfo) {
        AppLog.userInfo(LOGGER_NAME, session, "Get bus start info id - " + idBusStartInfo +
                " for generator id - " + idGenerator);
        BusStartInfo tempBSInfo = routeService.getBusStartInfo(idGenerator, idBusStartInfo);
        return new LightBusStartInfo(tempBSInfo);
    }


    @RequestMapping("ajax/editBusStartInfo")
    @ResponseBody
    public boolean editBusStartInfo(HttpServletRequest request, HttpSession session, Model model,
                                    @RequestParam(value = "idGenerator") int idGenerator,
                                    @RequestParam(value = "idBusStartInfo") int idBusStartInfo,
                                    @RequestParam(value = "startTimeMs") int startTimeMs,
                                    @RequestParam(value = "forwardDirection") boolean forwardDirection,
                                    @RequestParam(value = "repeatQuantity") int repeatQuantity,
                                    @RequestParam(value = "changeDirection") boolean changeDirection) {

        AppLog.userInfo(LOGGER_NAME, session, "Edit bus start info id - " + idBusStartInfo +
                " for generator id - " + idGenerator);
        userService.checkPermission(session);
        BusStartInfo busStartInfo = routeService.getBusStartInfo(idGenerator, idBusStartInfo);
        busStartInfo.setStartTimeMs(startTimeMs);
        busStartInfo.setForwardDirection(forwardDirection);
        busStartInfo.setRepeatQuantity(repeatQuantity);
        busStartInfo.setChangeDirection(changeDirection);

        routeService.updateBusStartInfo(busStartInfo);
        return true;
    }

}
