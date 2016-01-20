/**
 * springMVC controller
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.george_nika.simulation.controller.form.LoginForm;
import ua.george_nika.simulation.service.UserService;
import ua.george_nika.simulation.util.AppConst;
import ua.george_nika.simulation.util.AppLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SuppressWarnings("unused")

@Controller
public class UserController {

    private static String LOGGER_NAME = AppLog.CONTROLLER;

    @Autowired
    UserService userService;

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "LogOut");
        session.setAttribute(AppConst.LOGIN_IN_SESSION, false);
        session.setAttribute(AppConst.USER_IN_SESSION, "");
        return "main/generalMainPage";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpSession session, Model model) {
        AppLog.userInfo(LOGGER_NAME, session, "Go to Login Page");
        model.addAttribute("loginForm", new LoginForm());
        return "main/userLoginPage";
    }

    @RequestMapping("/loginAction")
    public String loginAction(HttpServletRequest request, HttpSession session, Model model,
                              @ModelAttribute("loginForm") LoginForm loginForm) {
        if (userService.loginAction(loginForm.getUserName(), loginForm.getPassword())) {
            AppLog.userInfo(LOGGER_NAME, session, "Success Login " + loginForm.getUserName());
            session.setAttribute(AppConst.LOGIN_IN_SESSION, true);
            session.setAttribute(AppConst.USER_IN_SESSION, loginForm.getUserName());
            return "main/generalMainPage";
        } else {
            AppLog.userInfo(LOGGER_NAME, session, "Failed Login " + loginForm.getUserName());
            loginForm.setPassword("");
            model.addAttribute("loginForm", loginForm);
            return "main/userLoginPage";
        }
    }
}
