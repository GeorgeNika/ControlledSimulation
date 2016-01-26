/**
 * Use for JAXB xml convertation
 */

package ua.george_nika.simulation.util;

import org.joda.time.DateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateTimeXmlAdapter extends XmlAdapter<String, DateTime> {


    @Override
    public DateTime unmarshal(String v) throws Exception {
        return new DateTime(v);
    }

    @Override
    public String marshal(DateTime v) throws Exception {
        return v.toString();
    }

}
