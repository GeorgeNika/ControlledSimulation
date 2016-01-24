/**
 * springMVC controller
 * after lecture  JavaDoc + UnitTest = Documentation
 */
package ua.george_nika.simulation.controller.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.george_nika.simulation.controller.light_ajax_info.entity.LightEntityHistory;
import ua.george_nika.simulation.dao.filter.EntityFilter;
import ua.george_nika.simulation.model.entity.*;
import ua.george_nika.simulation.service.entity.EntityHistoryService;
import ua.george_nika.simulation.service.entity.EntityInfoService;
import ua.george_nika.simulation.service.generator.GeneratorService;
import ua.george_nika.simulation.controller.ControllerFactory;
import ua.george_nika.simulation.controller.generator.GeneratorController;
import ua.george_nika.simulation.controller.light_ajax_info.entity.LightEntityInfo;
import ua.george_nika.simulation.util.AppLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")

@Controller
public class EntityMainController {
    private static String LOGGER_NAME = AppLog.CONTROLLER;

    @Autowired
    GeneratorController generatorController;
    @Autowired
    EntityInfoService entityInfoService;
    @Autowired
    GeneratorService generatorService;
    @Autowired
    EntityHistoryService entityHistoryService;

    @RequestMapping("/entityInfoPage/{entityInfoType}")
    public String entityInfoPage(HttpServletRequest request, HttpSession session, Model model,
                                 @PathVariable("entityInfoType") String entityInfoType) {
        AppLog.userInfo(LOGGER_NAME, session, "Go to entity info page. type - " + entityInfoType);
        EntityInfoExtraController entityInfoExtraController
                = ControllerFactory.getEntityInfoExtraControllerByType(entityInfoType);
        return "entity/" + entityInfoExtraController.getEntityInfoSetupJSPPage();
    }

    @RequestMapping("/entityMainHistoryPage")
    public String generatorMainHistoryPage(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Go to entity main history page");
        return "entity/entityMainHistoryPage";
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

    @RequestMapping("/ajax/getEntityHistoryListByFilter")
    @ResponseBody
    public List<LightEntityHistory> getEntityHistoryListByFilter
            (HttpServletRequest request, HttpSession session, Model model,
             @RequestParam(value = "filterIdExperiment") int filterIdExperiment,
             @RequestParam(value = "filterIdExperimentHistory") int filterIdExperimentHistory,
             @RequestParam(value = "filterIdGenerator") int filterIdGenerator,
             @RequestParam(value = "filterIdGeneratorHistory") int filterIdGeneratorHistory,
             @RequestParam(value = "filterIdEntityHistory") int filterIdEntityHistory,
             @RequestParam(value = "filterIdEntity") int filterIdEntity) {
        AppLog.userInfo(LOGGER_NAME, session, "Get generator history list by filter");
        EntityFilter entityFilter = new EntityFilter();
        entityFilter.setIdExperiment(filterIdExperiment);
        entityFilter.setIdExperimentHistory(filterIdExperimentHistory);
        entityFilter.setIdGenerator(filterIdGenerator);
        entityFilter.setIdGeneratorHistory(filterIdGeneratorHistory);
        entityFilter.setIdEntityHistory(filterIdEntityHistory);
        entityFilter.setIdEntity(filterIdEntity);
        List<EntityHistory> tempEntityHistoryList
                = entityHistoryService.getLazyEntityHistoryListByFilter(entityFilter);
        List<LightEntityHistory> resultLightEntityHistoryList = new ArrayList<>();
        for (EntityHistory loopEntityHistory : tempEntityHistoryList) {
            resultLightEntityHistoryList.add(new LightEntityHistory(loopEntityHistory));
        }
        return resultLightEntityHistoryList;
    }

    @RequestMapping("/entityHistoryPage/{idEntityHistory}")
    public String generatorHistoryPage(HttpServletRequest request, HttpSession session, Model model,
                                       @PathVariable("idEntityHistory") int idEntityHistory) {

        AppLog.userInfo(LOGGER_NAME, session, "Go to entity history page id history - " + idEntityHistory);
        EntityHistory tempEH = entityHistoryService.getEntityHistoryById(idEntityHistory);
        EntityExtraController entityExtraController =
                ControllerFactory.getEntityExtraControllerByType(tempEH.getEntityType());
        model.addAttribute("entityHistory", tempEH);
        entityExtraController.addExtraDataToHistoryPage(request, session, model, tempEH);
        String resultJSPPage = entityExtraController.getEntityHistoryJSPPage();
        return "entity/" + resultJSPPage;
    }
}
