/**
 * springMVC controller
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.george_nika.simulation.controller.ControllerFactory;
import ua.george_nika.simulation.controller.light_ajax_info.entity.LightEconomyHumanEntityInfo;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.model.entity.impl.EconomyHumanEntityInfo;
import ua.george_nika.simulation.service.UserService;
import ua.george_nika.simulation.service.entity.EntityInfoService;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.ClassTypeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

@Controller
public class EconomyHumanEntityInfoController implements EntityInfoExtraController {

    public static final String ENTITY_INFO_SETUP_JSP_PAGE = "economyHumanEntityInfoSetupPage";

    private static String entityInfoType = EconomyHumanEntityInfo.ENTITY_INFO_TYPE;
    private static String LOGGER_NAME = AppLog.CONTROLLER;
    private static String CLASS_NAME = EconomyHumanEntityInfoController.class.getSimpleName();

    static {
        ControllerFactory.registerEntityInfoControllerClassInFactory(entityInfoType,
                EconomyHumanEntityInfoController.class.getCanonicalName());
    }

    @Autowired
    EntityInfoService entityInfoService;
    @Autowired
    UserService userService;

    @Override
    public String getEntityInfoSetupJSPPage() {
        return ENTITY_INFO_SETUP_JSP_PAGE;
    }

    @RequestMapping("ajax/getEconomyHumanEntityInfoList")
    @ResponseBody
    public List<LightEconomyHumanEntityInfo> getEconomyHumanEntityInfoList(
            HttpServletRequest request, HttpSession session, Model model) {

        AppLog.userInfo(LOGGER_NAME, session, "Get economy human entity info list");
        List<LightEconomyHumanEntityInfo> resultLightInfoList = new ArrayList<>();
        for (EntityInfo loopEntityInfo : entityInfoService.getEntityInfoListByType(entityInfoType)) {
            EconomyHumanEntityInfo economyHumanEntityInfo
                    = ClassTypeUtil.getCheckedClass(loopEntityInfo, EconomyHumanEntityInfo.class);
            resultLightInfoList.add(new LightEconomyHumanEntityInfo(economyHumanEntityInfo));
        }
        return resultLightInfoList;
    }

    @RequestMapping("ajax/getEconomyHumanEntityInfo")
    @ResponseBody
    public LightEconomyHumanEntityInfo getEconomyHumanEntityInfo(
            HttpServletRequest request, HttpSession session, Model model,
            @RequestParam(value = "idEntityInfo") int idEntityInfo) {

        AppLog.userInfo(LOGGER_NAME, session, "Get economy human entity info id - " + idEntityInfo);
        EntityInfo entityInfo = entityInfoService.getEntityInfoByTypeById(entityInfoType, idEntityInfo);
        EconomyHumanEntityInfo economyHumanEntityInfo
                = ClassTypeUtil.getCheckedClass(entityInfo, EconomyHumanEntityInfo.class);
        return new LightEconomyHumanEntityInfo(economyHumanEntityInfo);
    }

    @RequestMapping("ajax/createEconomyHumanEntityInfo")
    @ResponseBody
    public boolean createEconomyHumanEntityInfo(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Create new economy human entity info");
        userService.checkPermission(session);
        entityInfoService.createEntityInfoByType(entityInfoType);
        return true;
    }

    @RequestMapping("ajax/deleteEconomyHumanEntityInfo")
    @ResponseBody
    public boolean deleteEconomyHumanEntityInfo(HttpServletRequest request, HttpSession session, Model model,
                                                @RequestParam(value = "idEntityInfo") int idEntityInfo) {
        AppLog.userInfo(LOGGER_NAME, session, "Delete economy human entity info id - " + idEntityInfo);
        userService.checkPermission(session);
        entityInfoService.deleteEntityInfoByTypeById(entityInfoType, idEntityInfo);
        return true;
    }

    @RequestMapping("ajax/editEconomyHumanEntityInfo")
    @ResponseBody
    public boolean editEconomyHumanEntityInfo(HttpServletRequest request, HttpSession session, Model model,
                                              @RequestParam(value = "idEntityInfo") int idEntityInfo,
                                              @RequestParam(value = "reasonablePriceInCent") int reasonablePriceInCent,
                                              @RequestParam(value = "maxPriceInCent") int maxPriceInCent,
                                              @RequestParam(value = "delayTimeToRemoveMs") int delayTimeToRemoveMs) {
        AppLog.userInfo(LOGGER_NAME, session, "Edit economy human entity info id - " + idEntityInfo);
        userService.checkPermission(session);
        EntityInfo entityInfo = entityInfoService.getEntityInfoByTypeById(entityInfoType, idEntityInfo);
        EconomyHumanEntityInfo economyHumanEntityInfo
                = ClassTypeUtil.getCheckedClass(entityInfo, EconomyHumanEntityInfo.class);
        economyHumanEntityInfo.setReasonablePriceInCent(reasonablePriceInCent);
        economyHumanEntityInfo.setMaxPriceInCent(maxPriceInCent);
        economyHumanEntityInfo.setDelayTimeToRemoveMs(delayTimeToRemoveMs);
        entityInfoService.updateEntityInfoByTypeById(entityInfoType, economyHumanEntityInfo);
        return true;
    }
}
