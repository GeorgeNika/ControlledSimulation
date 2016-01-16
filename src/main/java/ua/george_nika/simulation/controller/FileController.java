package ua.george_nika.simulation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.service.UserService;
import ua.george_nika.simulation.service.experiment.ExperimentService;
import ua.george_nika.simulation.util.AppLog;
import ua.george_nika.simulation.util.XmlUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * Created by george on 08.01.2016.
 */
@Controller
public class FileController {

    private static String LOGGER_NAME = AppLog.CONTROLLER;
    private static final int BUFFER_SIZE = 4096;

    @Autowired
    ExperimentService experimentService;
    @Autowired
    UserService userService;

    @RequestMapping("/downloadLogFile/{idExperimentHistory}")
    public void downloadLogFile(HttpServletRequest request, HttpSession session, HttpServletResponse response,
                                @PathVariable(value = "idExperimentHistory") int idExperimentHistory) {
        // todo right log
        AppLog.userInfo(LOGGER_NAME, session, "ertbrbgret");
        try {
            //todo right file name get from histiry by id
            String filePath = "..\\logs\\simulation\\model\\bbb.txt";
            // get absolute path of the application
            ServletContext context = request.getServletContext();
            String appPath = context.getRealPath("");
            System.out.println("appPath = " + appPath);

            // construct the complete absolute path of the file
            String fullPath = filePath;// appPath + filePath;
            File downloadFile = new File(fullPath);
            FileInputStream inputStream = new FileInputStream(downloadFile);

            // get MIME type of the file
            String mimeType = context.getMimeType(fullPath);
            if (mimeType == null) {
                // set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
            }
            System.out.println("MIME type: " + mimeType);

            // set content attributes for the response
            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());

            // set headers for the response
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);

            // get output stream of the response
            OutputStream outStream = response.getOutputStream();

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;

            // write bytes read from the input stream into the output stream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
