package ua.george_nika.simulation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.george_nika.simulation.service.experiment.ExperimentService;
import ua.george_nika.simulation.util.AppLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by george on 01.12.2015.
 */

@Controller
public class MainPageController {

    private static String LOGGER_NAME = AppLog.CONTROLLER;

    @Autowired
    ExperimentService experimentService;


    @RequestMapping("/generalMainPage")
    public String generalMainPage(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Go to General Main Page");
        return "main/generalMainPage";
    }

    @RequestMapping("/setupMainPage")
    public String setupMainPage(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Go to Setup Main Page");
        return "main/setupMainPage";
    }

    @RequestMapping("/runMainPage")
    public String runMainPage(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Go to Run Main Page");
        return "main/runMainPage";
    }

    @RequestMapping("/historyMainPage")
    public String historyMainPage(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Go to History Main Page");
        return "main/historyMainPage";
    }
}
