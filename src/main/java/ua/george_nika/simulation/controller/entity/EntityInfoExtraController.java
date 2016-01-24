/**
 * interface for extra controllers
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller.entity;

import org.springframework.ui.Model;
import ua.george_nika.simulation.model.entity.EntityHistory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SuppressWarnings("unused")

public interface EntityInfoExtraController {

    String getEntityInfoSetupJSPPage();

}
