package com.socialpoint.app.ipgeolocation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Scanner;
import java.util.logging.Logger;

public class IpLocator {

    private static final Logger logger = Logger.getLogger("IpLocator");
    private static String PATH = "IPCountry.SAMPLE.CSV";
    /**
     * Geo-locate the given IPV4 address.
     *
     * @param string ipAddress The IPV4 address
     * @return GeoLocation
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static GeoLocation geoLocate(String ipAddress) throws IOException {
        GeoLocation location = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = loader.getResourceAsStream(PATH);
                Scanner scanner = new Scanner(inputStream, "UTF-8")) {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            long ipNumber = getIpNumber(inetAddress);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                location = parseData(line);
                if (location.inRange(ipNumber)) {
                    break;
                }
                line = null;
                location = null;
            }

        }
        return location;
    }

    public static void setPath(String path) {
        PATH = path;
    }

    private static long getIpNumber(InetAddress bar) {
        ByteBuffer buffer = ByteBuffer.allocate(1024).order(ByteOrder.BIG_ENDIAN);
        buffer.put(new byte[] { 0,0,0,0 });
        buffer.put(bar.getAddress());
        buffer.position(0);
        return buffer.getLong();
    }

    private static GeoLocation parseData(String line) {
        GeoLocation location = new GeoLocation();
        int startIndex = line.indexOf("\"")+1;
        int endIndex = line.indexOf("\"", startIndex);
        location.setStartIpNumber(Long.parseLong(line.substring(startIndex, endIndex)));
        startIndex = line.indexOf("\"", endIndex+1)+1;
        endIndex = line.indexOf("\"", startIndex);
        location.setEndIpNumber(Long.parseLong(line.substring(startIndex, endIndex)));
        startIndex = line.indexOf("\"", endIndex+1)+1;
        endIndex = line.indexOf("\"", startIndex);
        location.setCountryCode(line.substring(startIndex, endIndex));
        startIndex = line.indexOf("\"", endIndex+1)+1;
        endIndex = line.indexOf("\"", startIndex);
        location.setCountryName(line.substring(startIndex, endIndex));
        return location;
    }

    public static void main(String[] args) {
        String ip = "217.168.171.95";
        if(args != null) {
            if (args.length == 1) {
                ip = args[0];
            }
            if (args.length == 2) {
                ip = args[0];
                setPath(args[1]);
            }
        }
        try {
            GeoLocation location = geoLocate(ip);
            System.out.println(location.getCountryName());
            System.out.println(Ip2LocationExporter.exportJSON(location));
            System.out.println(Ip2LocationExporter.exportXML(location));
            System.out.println(Ip2LocationExporter.exportYAML(location));
            System.out.println(Ip2LocationExporter.exportJsonToXML(Ip2LocationExporter.exportJSON(location)));
            System.out.println(Ip2LocationExporter.exportXMLtoJson(Ip2LocationExporter.exportXML(location)));
        } catch (IOException e) {
            logger.info("IOException: "+e.getMessage());
        }
    }
}
