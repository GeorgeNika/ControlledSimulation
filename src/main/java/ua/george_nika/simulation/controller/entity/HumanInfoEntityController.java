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
import ua.george_nika.simulation.model.entity.EntityHistory;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.model.entity.impl.HumanEntityInfo;
import ua.george_nika.simulation.service.UserService;
import ua.george_nika.simulation.service.entity.EntityInfoService;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.controller.ControllerFactory;
import ua.george_nika.simulation.controller.light_ajax_info.entity.LightHumanEntityInfo;
import ua.george_nika.simulation.util.ClassTypeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

@Controller
public class HumanInfoEntityController implements EntityInfoExtraController {

    public static final String ENTITY_INFO_SETUP_JSP_PAGE = "humanEntityInfoSetupPage";

    private static String entityInfoType = HumanEntityInfo.ENTITY_INFO_TYPE;
    private static String LOGGER_NAME = AppLog.CONTROLLER;
    private static String CLASS_NAME = HumanInfoEntityController.class.getSimpleName();

    static {
        ControllerFactory.registerEntityInfoControllerClassInFactory(entityInfoType,
                HumanInfoEntityController.class.getCanonicalName());
    }

    @Autowired
    EntityInfoService entityInfoService;
    @Autowired
    UserService userService;

    @Override
    public String getEntityInfoSetupJSPPage() {
        return ENTITY_INFO_SETUP_JSP_PAGE;
    }

    @RequestMapping("ajax/getHumanEntityInfoList")
    @ResponseBody
    public List<LightHumanEntityInfo> getHumanEntityInfoList(
            HttpServletRequest request, HttpSession session, Model model) {

        AppLog.userInfo(LOGGER_NAME, session, "Get human entity info list");
        List<LightHumanEntityInfo> resultLightInfoList = new ArrayList<>();
        for (EntityInfo loopEntityInfo : entityInfoService.getEntityInfoListByType(entityInfoType)) {
            HumanEntityInfo humanEntityInfo = ClassTypeUtil.getCheckedClass(loopEntityInfo, HumanEntityInfo.class);
            resultLightInfoList.add(new LightHumanEntityInfo(humanEntityInfo));
        }
        return resultLightInfoList;
    }

    @RequestMapping("ajax/getHumanEntityInfo")
    @ResponseBody
    public LightHumanEntityInfo getHumanEntityInfo(HttpServletRequest request, HttpSession session, Model model,
                                                   @RequestParam(value = "idEntityInfo") int idEntityInfo) {
        AppLog.userInfo(LOGGER_NAME, session, "Get human entity info id - " + idEntityInfo);
        EntityInfo entityInfo = entityInfoService.getEntityInfoByTypeById(
                HumanEntityInfo.ENTITY_INFO_TYPE, idEntityInfo);
        HumanEntityInfo humanEntityInfo = ClassTypeUtil.getCheckedClass(entityInfo, HumanEntityInfo.class);
        return new LightHumanEntityInfo(humanEntityInfo);
    }

    @RequestMapping("ajax/createHumanEntityInfo")
    @ResponseBody
    public boolean createHumanEntityInfo(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Create new human entity info");
        userService.checkPermission(session);
        entityInfoService.createEntityInfoByType(entityInfoType);
        return true;
    }

    @RequestMapping("ajax/deleteHumanEntityInfo")
    @ResponseBody
    public boolean deleteHumanEntityInfo(HttpServletRequest request, HttpSession session, Model model,
                                         @RequestParam(value = "idEntityInfo") int idEntityInfo) {
        AppLog.userInfo(LOGGER_NAME, session, "Delete human entity info id - " + idEntityInfo);
        userService.checkPermission(session);
        entityInfoService.deleteEntityInfoByTypeById(entityInfoType, idEntityInfo);
        return true;
    }

    @RequestMapping("ajax/editHumanEntityInfo")
    @ResponseBody
    public boolean editHumanEntityInfo(HttpServletRequest request, HttpSession session, Model model,
                                       @RequestParam(value = "idEntityInfo") int idEntityInfo,
                                       @RequestParam(value = "reasonablePriceInCent") int reasonablePriceInCent,
                                       @RequestParam(value = "delayTimeToRemoveMs") int delayTimeToRemoveMs) {
        AppLog.userInfo(LOGGER_NAME, session, "Edit human entity info id - " + idEntityInfo);
        userService.checkPermission(session);
        EntityInfo entityInfo = entityInfoService.getEntityInfoByTypeById(
                HumanEntityInfo.ENTITY_INFO_TYPE, idEntityInfo);
        HumanEntityInfo humanEntityInfo = ClassTypeUtil.getCheckedClass(entityInfo, HumanEntityInfo.class);
        humanEntityInfo.setReasonablePriceInCent(reasonablePriceInCent);
        humanEntityInfo.setDelayTimeToRemoveMs(delayTimeToRemoveMs);
        entityInfoService.updateEntityInfoByTypeById(entityInfoType, humanEntityInfo);
        return true;
    }
}
