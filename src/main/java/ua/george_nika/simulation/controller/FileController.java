/**
 * springMVC controller
 * after lecture  JavaDoc + UnitTest = Documentation
 */

package ua.george_nika.simulation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.george_nika.simulation.controller.error.DownloadFileException;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentHistory;
import ua.george_nika.simulation.service.UserService;
import ua.george_nika.simulation.service.experiment.ExperimentHistoryService;
import ua.george_nika.simulation.service.experiment.ExperimentService;
import ua.george_nika.simulation.util.AppConst;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.XmlUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

@Controller
public class FileController {

    private static String LOGGER_NAME = AppLog.CONTROLLER;
    private static String CLASS_NAME = FileController.class.getCanonicalName();
    private static final int BUFFER_SIZE = 4096;

    @Autowired
    ExperimentService experimentService;
    @Autowired
    ExperimentHistoryService experimentHistoryService;
    @Autowired
    UserService userService;


    @RequestMapping("/downloadXmlFile/{idExperimentHistory}")
    public void downloadXmlFile(HttpServletRequest request, HttpSession session, HttpServletResponse response,
                                @PathVariable(value = "idExperimentHistory") int idExperimentHistory) {
        ExperimentHistory expHistory = experimentHistoryService.getLazyExperimentHistoryById(idExperimentHistory);
        AppLog.userInfo(LOGGER_NAME, session, "Download Xml file " + expHistory.getXmlFile()
                + " from experiment with history id - " + idExperimentHistory);
        downloadFile(request, session, response, AppConst.getPathXml(), expHistory.getXmlFile());
    }

    @RequestMapping("/downloadLogFile/{idExperimentHistory}")
    public void downloadLogFile(HttpServletRequest request, HttpSession session, HttpServletResponse response,
                                @PathVariable(value = "idExperimentHistory") int idExperimentHistory) {
        ExperimentHistory expHistory =   experimentHistoryService.getLazyExperimentHistoryById(idExperimentHistory);
        AppLog.userInfo(LOGGER_NAME, session, "Download Log file " + expHistory.getLogFile()
                + " from experiment with history id - " + idExperimentHistory);
        downloadFile(request, session, response, AppConst.getPathLog(), expHistory.getLogFile());
    }

    // put file in response
    protected void downloadFile(HttpServletRequest request, HttpSession session, HttpServletResponse response,
                                String filePath, String fileName) {
        try {
            // get absolute path of the application
            ServletContext context = request.getServletContext();

            // construct the complete absolute path of the file
            String fullPath = filePath + fileName;// appPath + filePath;
            File downloadFile = new File(fullPath);
            FileInputStream inputStream = new FileInputStream(downloadFile);

            // get MIME type of the file
            String mimeType = context.getMimeType(fullPath);
            if (mimeType == null) {
                // set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
            }

            // set content attributes for the response
            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());

            // set headers for the response
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"",
                    new String(fileName.getBytes(), "latin1"));
            response.setHeader(headerKey, headerValue);

            // get output stream of the response
            OutputStream outStream = response.getOutputStream();

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            // write bytes read from the input stream into the output stream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outStream.close();
        } catch (FileNotFoundException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error. File not found for download - " + filePath + fileName, ex);
            throw new DownloadFileException("Error. File not found for download - " + filePath + fileName);
        } catch (IOException ex) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error Input-Output. When download - " + filePath + fileName, ex);
            throw new DownloadFileException("Error Input-Output. When download - " + filePath + fileName);
        }
    }

    @RequestMapping(value = "uploadFile")
    public String uploadFile(HttpServletRequest request, HttpSession session, Model model,
                             @RequestParam MultipartFile file) {
        AppLog.userInfo(LOGGER_NAME, session, "Start experiment from xml - " + file.getContentType());
        userService.checkPermission(session);
        Experiment experiment = XmlUtil.loadExperimentFromMultipartFile(file);
        experiment.setIdExperiment(0); // clear id for understanding
        experimentService.startExperiment(experiment);
        return "main/runMainPage";
    }

}
