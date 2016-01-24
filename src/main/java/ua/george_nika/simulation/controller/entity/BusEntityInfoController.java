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
import ua.george_nika.simulation.controller.light_ajax_info.entity.LightBusEntityInfo;
import ua.george_nika.simulation.model.entity.EntityInfo;
import ua.george_nika.simulation.model.entity.impl.BusEntityInfo;
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
public class BusEntityInfoController implements EntityInfoExtraController {

    public static final String ENTITY_INFO_SETUP_JSP_PAGE = "busEntityInfoSetupPage";

    private static String entityInfoType = BusEntityInfo.ENTITY_INFO_TYPE;
    private static String LOGGER_NAME = AppLog.CONTROLLER;
    private static String CLASS_NAME = EntityExtraController.class.getSimpleName();

    @Autowired
    EntityInfoService entityInfoService;
    @Autowired
    UserService userService;

    static {
        ControllerFactory.registerEntityInfoControllerClassInFactory(entityInfoType,
                EntityExtraController.class.getCanonicalName());
    }

    @Override
    public String getEntityInfoSetupJSPPage() {
        return ENTITY_INFO_SETUP_JSP_PAGE;
    }


    @RequestMapping("ajax/getBusEntityInfoList")
    @ResponseBody
    public List<LightBusEntityInfo> getBusEntityInfoList(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Get bus entity info list");
        List<LightBusEntityInfo> resultLightInfoList = new ArrayList<>();
        for (EntityInfo loopEntityInfo : entityInfoService.getEntityInfoListByType(BusEntityInfo.ENTITY_INFO_TYPE)) {
            BusEntityInfo busEntityInfo = ClassTypeUtil.getCheckedClass(loopEntityInfo, BusEntityInfo.class);
            resultLightInfoList.add(new LightBusEntityInfo(busEntityInfo));
        }
        return resultLightInfoList;
    }

    @RequestMapping("ajax/getBusEntityInfo")
    @ResponseBody
    public LightBusEntityInfo getBusEntityInfo(HttpServletRequest request, HttpSession session, Model model,
                                               @RequestParam(value = "idEntityInfo") int idEntityInfo) {
        AppLog.userInfo(LOGGER_NAME, session, "Get bus entity info id - " + idEntityInfo);
        EntityInfo entityInfo = entityInfoService.getEntityInfoByTypeById(
                BusEntityInfo.ENTITY_INFO_TYPE, idEntityInfo);

        BusEntityInfo busEntityInfo = ClassTypeUtil.getCheckedClass(entityInfo, BusEntityInfo.class);
        return new LightBusEntityInfo(busEntityInfo);
    }

    @RequestMapping("ajax/createBusEntityInfo")
    @ResponseBody
    public boolean createBusEntityInfo(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Create new bus entity info");
        userService.checkPermission(session);
        entityInfoService.createEntityInfoByType(BusEntityInfo.ENTITY_INFO_TYPE);
        return true;
    }

    @RequestMapping("ajax/deleteBusEntityInfo")
    @ResponseBody
    public boolean deleteBusEntityInfo(HttpServletRequest request, HttpSession session, Model model,
                                       @RequestParam(value = "idEntityInfo") int idEntityInfo) {
        AppLog.userInfo(LOGGER_NAME, session, "Delete bus entity info id - " + idEntityInfo);
        userService.checkPermission(session);
        entityInfoService.deleteEntityInfoByTypeById(BusEntityInfo.ENTITY_INFO_TYPE, idEntityInfo);
        return true;
    }

    @RequestMapping("ajax/editBusEntityInfo")
    @ResponseBody
    public boolean editBusEntityInfo(HttpServletRequest request, HttpSession session, Model model,
                                     @RequestParam(value = "idEntityInfo") int idEntityInfo,
                                     @RequestParam(value = "capacity") int capacity,
                                     @RequestParam(value = "priceInCent") int priceInCent) {
        AppLog.userInfo(LOGGER_NAME, session, "Edit bus entity info id - " + idEntityInfo);
        userService.checkPermission(session);
        EntityInfo entityInfo = entityInfoService.getEntityInfoByTypeById(BusEntityInfo.ENTITY_INFO_TYPE, idEntityInfo);
        BusEntityInfo busEntityInfo = ClassTypeUtil.getCheckedClass(entityInfo, BusEntityInfo.class);
        busEntityInfo.setCapacity(capacity);
        busEntityInfo.setPriceInCent(priceInCent);
        entityInfoService.updateEntityInfoByTypeById(BusEntityInfo.ENTITY_INFO_TYPE, busEntityInfo);
        return true;
    }
}
