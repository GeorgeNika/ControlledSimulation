package ua.george_nika.simulation.controller.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.george_nika.simulation.model.entity.EntityFactory;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.model.entity.EntityInfoFactory;
import ua.george_nika.simulation.service.entity.EntityInfoService;
import ua.george_nika.simulation.service.generator.GeneratorService;
import ua.george_nika.simulation.controller.ControllerFactory;
import ua.george_nika.simulation.controller.generator.GeneratorController;
import ua.george_nika.simulation.controller.light_ajax_info.LightEntityInfo;
import ua.george_nika.simulation.util.AppLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 15.12.2015.
 */
@Controller
public class EntityMainController {
    private static String LOGGER_NAME = AppLog.CONTROLLER;

    @Autowired
    GeneratorController generatorController;

    @Autowired
    EntityInfoService entityInfoService;

    @Autowired
    GeneratorService generatorService;

    @RequestMapping("/entityInfoPage/{entityInfoType}")
    public String entityInfoPage(HttpServletRequest request, HttpSession session, Model model,
                                 @PathVariable("entityInfoType") String entityInfoType) {
        AppLog.userInfo(LOGGER_NAME, session, "Go to entity info page. type - " + entityInfoType);
        EntityController entityController = ControllerFactory.getEntityInfoControllerByType(entityInfoType);
        return "entity/" + entityController.getEntityInfoSetupJSPPage();
    }

    @RequestMapping("/ajax/getEntityInfoListByEntityType")
    @ResponseBody
    public List<LightEntityInfo> getEntityInfoListByEntityType(HttpServletRequest request, HttpSession session,
                                                               Model model,
                                                               @RequestParam(value = "entityType") String entityType) {
        AppLog.userInfo(LOGGER_NAME, session, "Get entity info list by entity type. type - " + entityType);
        List<EntityInfo> tempEIList = entityInfoService.getEntityInfoListByEntityType(entityType);
        List<LightEntityInfo> resultList = new ArrayList<>();
        for (EntityInfo loopEI : tempEIList) {
            resultList.add(new LightEntityInfo(loopEI));
        }
        return resultList;
    }

    @RequestMapping("/ajax/getEntityInfoTypeList")
    @ResponseBody
    public List<String> getEntityInfoTypeList(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Get entity info type list");
        List<String> resultEntityInfoTypeList = new ArrayList<>();
        resultEntityInfoTypeList.addAll(EntityInfoFactory.getAllRegisteredEntityInfoType());
        return resultEntityInfoTypeList;
    }

    @RequestMapping("/ajax/getEntityTypeList")
    @ResponseBody
    public List<String> getEntityTypeList(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Get entity type list");
        List<String> resultEntityTypeList = new ArrayList<>();
        resultEntityTypeList.addAll(EntityFactory.getAllRegisteredEntityType());
        return resultEntityTypeList;
    }


}
