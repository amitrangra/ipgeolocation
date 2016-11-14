package com.socialpoint.app.ipgeolocation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Ip2LocationExporterTest {

    private static GeoLocation location;

    @BeforeClass
    public static void method() throws IOException {
        location = IpLocator.geoLocate("217.168.171.95");
    }

    @Test
    public void testExportJSONWithNullLocation() {
        assertNull(Ip2LocationExporter.exportJSON(null));
    }

    @Test
    public void testExportJSONWithValidLocation() {
        String jsonString = Ip2LocationExporter.exportJSON(location);
        assertNotNull(jsonString);
        assertTrue(isJSONValid(jsonString));
    }

    @Test
    public void testExportXMLWithNullLocation() {
        assertNull(Ip2LocationExporter.exportXML(null));
    }

    @Test
    public void testExportXMLWithValidLocation() {
        assertNotNull(Ip2LocationExporter.exportXML(location));
    }

    @Test
    public void testExportYAMLWithNullLocation() {
        assertNull(Ip2LocationExporter.exportYAML(null));
    }

    @Test
    public void testExportYAMLWithValidLocation() {
        assertNotNull(Ip2LocationExporter.exportYAML(location));
    }

    @Test
    public void testExportJsonToXMLWithNullLocation() {
        assertNull(Ip2LocationExporter.exportJsonToXML(null));
    }

    @Test
    public void testExportJsonToXMLWithValidLocation() {
        assertNotNull(Ip2LocationExporter.exportJsonToXML(Ip2LocationExporter.exportJSON(location)));
    }

    @Test
    public void testExportXMLToJsonWithNullLocation() {
        assertNull(Ip2LocationExporter.exportXMLtoJson(null));
    }

    @Test
    public void testExportXMLToJsonWithValidLocation() {
        assertNotNull(Ip2LocationExporter.exportXMLtoJson(Ip2LocationExporter.exportXML(location)));
    }

    private static boolean isJSONValid(String jsonInString) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
