package ua.george_nika.simulation.controller.generator;

import org.springframework.ui.Model;
import ua.george_nika.simulation.model.generator.Generator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by george on 16.12.2015.
 */
public interface GeneratorExtraController {

    void addExtraData(HttpServletRequest request, HttpSession session, Model model, Generator generator);

    String getGeneratorJSPPage();
}
