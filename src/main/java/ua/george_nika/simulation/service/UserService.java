package ua.george_nika.simulation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.george_nika.simulation.service.error.AccessDeniedException;
import ua.george_nika.simulation.dao.user.UserDao;
import ua.george_nika.simulation.service.error.LoginActionException;
import ua.george_nika.simulation.util.AppConst;
import ua.george_nika.simulation.util.AppLog;

import javax.servlet.http.HttpSession;

/**
 * Created by george on 30.12.2015.
 */
@Service
public class UserService {

    private static String LOGGER_NAME = AppLog.SERVICE;
    private static String CLASS_NAME = UserService.class.getName();

    @Autowired
    UserDao userDao;

    public boolean loginAction(String userName, String password) {
        try {
            if (userDao.isExist(userName, password)) {
                return true;
            } else {
                return false;
            }
        } catch (RuntimeException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in login action", ex);
            throw new LoginActionException("Error in login action");
        }
    }

    public void checkPermission(HttpSession session) {
        try {
            Object login = session.getAttribute(AppConst.LOGIN_IN_SESSION);
            if ((login == null) || (!(boolean) login)) {
                throw new AccessDeniedException();
            }
        }catch (RuntimeException ex){
            if (ex instanceof AccessDeniedException) {
                AppLog.userInfo(AppLog.CONTROLLER, session, "Try execute action without permission.");
                throw new AccessDeniedException("Try execute action without permission.");
            } else {
                AppLog.error(LOGGER_NAME, CLASS_NAME, "Error in check permission", ex);
                throw new LoginActionException("Error in check permission");
            }
        }
    }


}
