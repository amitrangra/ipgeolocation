package com.socialpoint.app.ipgeolocation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Test;

public class IpLocatorTest {

    @Test
    public void testGeoLocateWithNullLocation() throws IOException {
        assertNull(IpLocator.geoLocate(null));
    }

    @Test
    public void testGeoLocateWithValidLocation() throws IOException {
        GeoLocation location = IpLocator.geoLocate("217.168.171.95");
        assertNotNull(location);
        assertEquals("MT", location.getCountryCode());
    }
}
