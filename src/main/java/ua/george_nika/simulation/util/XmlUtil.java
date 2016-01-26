/**
 * Work`s with xml (JAXB)
 */

package ua.george_nika.simulation.util;

import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;
import ua.george_nika.simulation.model.entity.EntityInfoFactory;
import ua.george_nika.simulation.model.experiment.Experiment;
import ua.george_nika.simulation.model.experiment.ExperimentFactory;
import ua.george_nika.simulation.model.generator.GeneratorFactory;
import ua.george_nika.simulation.model.generator.RelatedGeneratorData;
import ua.george_nika.simulation.util.error.GetClassForXmlException;
import ua.george_nika.simulation.util.error.SaveLoadXmlException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class XmlUtil {

    private static String LOGGER_NAME = AppLog.UTIL;
    private static String CLASS_NAME = XmlUtil.class.getSimpleName();


    public static synchronized String saveExperimentToXml(Experiment experiment, String infoString) {
        String fileName = getFileName(infoString);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(getClassArrayOfAllRegisteredClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(experiment, new FileWriter(AppConst.getPathExperimentXml() + fileName));
        } catch (JAXBException | IOException e) {
            throw new SaveLoadXmlException(LOGGER_NAME, CLASS_NAME, "Error. Save experiment to xml file - "
                    + fileName + " experiment N - " + experiment.getIdExperiment(), e);
        }
        return fileName;
    }

    public static synchronized Experiment loadExperimentFromMultipartFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Experiment resultExperiment;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(getClassArrayOfAllRegisteredClass());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Reader reader = new InputStreamReader(file.getInputStream());
            Object temp = unmarshaller.unmarshal(reader);
            resultExperiment = ClassTypeUtil.getCheckedClass(temp, Experiment.class);
        } catch (JAXBException | IOException e) {
            throw new SaveLoadXmlException(LOGGER_NAME, CLASS_NAME, "Error. Load experiment from xml file - "
                    + fileName, e);
        }
        return resultExperiment;
    }

    private static synchronized Class[] getClassArrayOfAllRegisteredClass() {
        Collection<String> stringClassName = new ArrayList<>();
        ArrayList<Class> resultClassList = new ArrayList<>();

        stringClassName.add(RelatedGeneratorData.class.getCanonicalName());
        stringClassName.addAll(EntityInfoFactory.getAllRegisteredEntityInfoClassName());
        stringClassName.addAll(GeneratorFactory.getAllRegisteredGeneratorClassName());
        stringClassName.addAll(ExperimentFactory.getAllRegisteredExperimentClassName());
        try {
            for (String loopString : stringClassName) {
                resultClassList.add(Class.forName(loopString));
            }
        } catch (ClassNotFoundException e) {
            throw new GetClassForXmlException(LOGGER_NAME, CLASS_NAME, "Class not found. Class list - "
                    + stringClassName.toString(), e);
        }
        return resultClassList.toArray(new Class[resultClassList.size()]);
    }

    private static String getFileName(String addInfo) {
        final String DATE_TIME_FORMAT = "yyyy_MM_dd__HH_mm_ss";
        String dateTimeInfo = new DateTime().toString(DATE_TIME_FORMAT);
        return "Xml " + addInfo + " " + dateTimeInfo + ".xml";
    }
}
