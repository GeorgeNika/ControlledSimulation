/**
 * interface for extra controllers
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.generator;

import org.springframework.ui.Model;
import ua.george_nika.simulation.model.generator.Generator;
import ua.george_nika.simulation.model.generator.GeneratorHistory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface GeneratorExtraController {

    void addExtraDataToSetupPage(HttpServletRequest request, HttpSession session, Model model, Generator generator);

    void addExtraDataToRunPage(HttpServletRequest request, HttpSession session, Model model, Generator generator);

    void addExtraDataToHistoryPage(HttpServletRequest request, HttpSession session, Model model,
                                   GeneratorHistory generatorHistory);

    String getGeneratorSetupJSPPage();

    String getGeneratorRunJSPPage();

    String getGeneratorHistoryJSPPage();

}
