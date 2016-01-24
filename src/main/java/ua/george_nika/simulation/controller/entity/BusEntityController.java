/**
 * springMVC controller
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ua.george_nika.simulation.model.entity.EntityHistory;
import ua.george_nika.simulation.model.entity.impl.BusEntity;
import ua.george_nika.simulation.service.UserService;
import ua.george_nika.simulation.service.entity.EntityInfoService;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.controller.ControllerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

@Controller
public class BusEntityController implements EntityExtraController {

    public static final String ENTITY_INFO_RUN_JSP_PAGE = "busEntityRunPage";
    public static final String ENTITY_INFO_HISTORY_JSP_PAGE = "busEntityHistoryPage";

    private static String entityInfoType = BusEntity.ENTITY_TYPE;
    private static String LOGGER_NAME = AppLog.CONTROLLER;
    private static String CLASS_NAME = EntityExtraController.class.getSimpleName();

    @Autowired
    EntityInfoService entityInfoService;
    @Autowired
    UserService userService;

    static {
        ControllerFactory.registerEntityControllerClassInFactory(entityInfoType,
                BusEntityController.class.getCanonicalName());
    }

    @Override
    public String getEntityRunJSPPage() {
        return ENTITY_INFO_RUN_JSP_PAGE;
    }

    @Override
    public String getEntityHistoryJSPPage() {
        return ENTITY_INFO_HISTORY_JSP_PAGE;
    }

    @Override
    public void addExtraDataToHistoryPage(HttpServletRequest request, HttpSession session, Model model, EntityHistory entityHistory) {
        // do nothing
    }

}
