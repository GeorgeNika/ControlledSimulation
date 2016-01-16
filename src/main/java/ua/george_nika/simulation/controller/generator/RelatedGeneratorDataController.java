package ua.george_nika.simulation.controller.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.george_nika.simulation.service.UserService;
import ua.george_nika.simulation.service.generator.RelatedGeneratorDataService;
import ua.george_nika.simulation.util.AppLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by george on 20.12.2015.
 */
@Controller
public class RelatedGeneratorDataController {
    private static String LOGGER_NAME = AppLog.CONTROLLER;

    @Autowired
    RelatedGeneratorDataService relatedGeneratorDataService;

    @Autowired
    UserService userService;

    @RequestMapping("ajax/upRelatedGeneratorData")
    @ResponseBody
    public boolean upRelatedGeneratorData(HttpServletRequest request, HttpSession session, Model model,
                                          @RequestParam(value = "idGenerator") int idGenerator,
                                          @RequestParam(value = "position") int position) {
        AppLog.userInfo(LOGGER_NAME, session, "Up position - " + position + " in generator id - " + idGenerator);
        userService.checkPermission(session);
        relatedGeneratorDataService.upPositionRelatedGeneratorData(idGenerator, position);
        return true;
    }


    @RequestMapping("ajax/downRelatedGeneratorData")
    @ResponseBody
    public boolean downRelatedGeneratorData(HttpServletRequest request, HttpSession session, Model model,
                                            @RequestParam(value = "idGenerator") int idGenerator,
                                            @RequestParam(value = "position") int position) {
        AppLog.userInfo(LOGGER_NAME, session, "Down position - " + position + " in generator id - " + idGenerator);
        userService.checkPermission(session);
        relatedGeneratorDataService.downPositionRelatedGeneratorData(idGenerator, position);
        return true;
    }

    @RequestMapping("ajax/deleteRelatedGeneratorData")
    @ResponseBody
    public boolean deleteRelatedGeneratorData(HttpServletRequest request, HttpSession session, Model model,
                                              @RequestParam(value = "idGenerator") int idGenerator,
                                              @RequestParam(value = "position") int position) {
        AppLog.userInfo(LOGGER_NAME, session, "Delete position - " + position + " in generator id - " + idGenerator);
        userService.checkPermission(session);
        relatedGeneratorDataService.deleteRelatedGeneratorData(idGenerator, position);
        return true;
    }


    @RequestMapping("ajax/setDelayForRelatedGeneratorData")
    @ResponseBody
    public boolean setDelayForRelatedGeneratorData(HttpServletRequest request, HttpSession session, Model model,
                                                   @RequestParam(value = "idGenerator") int idGenerator,
                                                   @RequestParam(value = "position") int position,
                                                   @RequestParam(value = "delayMs") long delayMs) {
        AppLog.userInfo(LOGGER_NAME, session, "Set delay for position - " + position +
                " in generator id - " + idGenerator);
        userService.checkPermission(session);
        relatedGeneratorDataService.setDelayForRelatedGeneratorData(idGenerator, position, delayMs);
        return true;
    }

    @RequestMapping("ajax/createRelatedGeneratorData")
    @ResponseBody
    public boolean createRelatedGeneratorData(HttpServletRequest request, HttpSession session, Model model,
                                              @RequestParam(value = "idGenerator") int idGenerator,
                                              @RequestParam(value = "idRelatedGenerator") int idRelatedGenerator) {
        AppLog.userInfo(LOGGER_NAME, session, "Create new relation for generator id - " + idGenerator +
                " with related generator id - " + idRelatedGenerator);
        userService.checkPermission(session);
        relatedGeneratorDataService.createRelatedGeneratorData(idGenerator, idRelatedGenerator);
        return true;
    }

    @RequestMapping("ajax/updateRelatedGeneratorData")
    @ResponseBody
    public boolean updateRelatedGeneratorData(HttpServletRequest request, HttpSession session, Model model,
                                              @RequestParam(value = "idGenerator") int idGenerator,
                                              @RequestParam(value = "idRelatedGenerator") int idRelatedGenerator,
                                              @RequestParam(value = "position") int position) {
        AppLog.userInfo(LOGGER_NAME, session, "Update related generator id - " + idRelatedGenerator +
                " data in position - " + position + " for generator id - " + idGenerator);
        userService.checkPermission(session);
        relatedGeneratorDataService.updateRelatedGeneratorData(idGenerator, idRelatedGenerator, position);
        return true;
    }
}
