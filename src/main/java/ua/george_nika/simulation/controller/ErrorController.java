/**
 * springMVC controller
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.george_nika.simulation.util.AppLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

@Controller
public class ErrorController {

    private static String LOGGER_NAME = AppLog.CONTROLLER;
    private static String CLASS_NAME = ErrorController.class.getCanonicalName();

    @RequestMapping(value = "/error")
    public String error(HttpServletRequest request, HttpSession session, Model model,
                        @RequestParam("info") String info) {
        model.addAttribute("errorMessage", info);
        AppLog.userInfo(LOGGER_NAME, session, "Go to error page with info " + info);
        AppLog.error(LOGGER_NAME, CLASS_NAME, "Error. " + info);
        return "main/errorPage";
    }
}

