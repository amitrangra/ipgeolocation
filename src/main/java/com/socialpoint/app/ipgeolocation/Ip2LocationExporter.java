package com.socialpoint.app.ipgeolocation;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.codehaus.jackson.map.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Ip2LocationExporter {
    private static final Logger logger = Logger.getLogger("Ip2LocationExporter");

    public static String exportJSON(GeoLocation location) {
        String jsonString = null;
        if (location != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                jsonString = mapper.writeValueAsString(location);
            } catch (IOException e) {
                logger.info("IOException: "+e.getMessage());
            }
        }
        return jsonString;
    }

    public static String exportXML(GeoLocation location) {
        String xmlString = null;
        if (location != null) {
            try {
                JAXBContext context = JAXBContext.newInstance(location.getClass());
                Marshaller m = context.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                StringWriter sw = new StringWriter();
                m.marshal(location, sw);
                xmlString = sw.toString();
            } catch (JAXBException e) {
                logger.info("JAXBException: "+e.getMessage());
            }
        }
        return xmlString;
    }

    public static String exportYAML(GeoLocation location) {
        String yamlString = null;
        if (location != null) {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("MyClass", location);
            Yaml yaml = new Yaml();
            yamlString = yaml.dump(location);
        }
        return yamlString;
    }

    public static String exportJsonToXML(String jsonString) {
        String xmlString = null;
        if (jsonString != null) {
            ObjectMapper jsonMapper = new ObjectMapper();
            try {
                GeoLocation location = jsonMapper.readValue(jsonString, GeoLocation.class);
                XmlMapper xmlMapper = new XmlMapper();
                xmlString = xmlMapper.writeValueAsString(location);
            } catch (IOException e) {
                logger.info("IOException: "+e.getMessage());
            }
        }
        return xmlString;
    }

    public static String exportXMLtoJson(String xmlString) {
        String jsonString = null;
        if (xmlString != null) {
            XmlMapper xmlMapper = new XmlMapper();
            try {
                GeoLocation location = xmlMapper.readValue(xmlString, GeoLocation.class);
                ObjectMapper jsonMapper = new ObjectMapper();
                jsonString = jsonMapper.writeValueAsString(location);
            } catch (IOException e) {
                logger.info("IOException: "+e.getMessage());
            }
        }
        return jsonString;
    }
}
