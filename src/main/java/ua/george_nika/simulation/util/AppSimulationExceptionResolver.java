package ua.george_nika.simulation.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import ua.george_nika.simulation.service.error.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by george on 01.01.2016.
 */
@Component
public class AppSimulationExceptionResolver extends SimpleMappingExceptionResolver {
    private static String CLASS_NAME = AppSimulationExceptionResolver.class.getCanonicalName();

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object objectHandler,
                                         Exception ex) {
        try {
            // todo in future. Convert all user-friendly exception to UserException
            // and show they message in error page.
            HttpSession session = request.getSession();
            Throwable causeException = ex.getCause() == null ? ex : ex.getCause();
            if (causeException instanceof AccessDeniedException) {
                AppLog.userInfo(AppLog.CONTROLLER, session, " Access denied in " + request.getRequestURI());
                return new ModelAndView("redirect:/login");
            }
            AppLog.error(AppLog.CONTROLLER, CLASS_NAME, "Error", ex);
            return new ModelAndView("redirect:/error?info=" + request.getRequestURI());
        } catch (Exception e) {
            AppLog.error(AppLog.CONTROLLER, CLASS_NAME, "Can't send error.", ex);
            return new ModelAndView("redirect:/generalMainPage");
        }
    }
}
