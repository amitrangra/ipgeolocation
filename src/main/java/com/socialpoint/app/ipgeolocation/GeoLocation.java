package com.socialpoint.app.ipgeolocation;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeoLocation {
    private long startIpNumber;
    private long endIpNumber;
    private String countryCode;
    private String countryName;

    public boolean inRange(long ipNumber) {
        return (ipNumber >= startIpNumber && ipNumber <= endIpNumber);
    }

    /**
     * @return the startIpNumber
     */
    public long getStartIpNumber() {
        return startIpNumber;
    }
    /**
     * @param parStartIpNumber the startIpNumber to set
     */
    public void setStartIpNumber(long parStartIpNumber) {
        startIpNumber = parStartIpNumber;
    }
    /**
     * @return the endIpNumber
     */
    public long getEndIpNumber() {
        return endIpNumber;
    }
    /**
     * @param parEndIpNumber the endIpNumber to set
     */
    public void setEndIpNumber(long parEndIpNumber) {
        endIpNumber = parEndIpNumber;
    }
    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }
    /**
     * @param parCountryCode the countryCode to set
     */
    public void setCountryCode(String parCountryCode) {
        countryCode = parCountryCode;
    }
    /**
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }
    /**
     * @param parCountryName the countryName to set
     */
    public void setCountryName(String parCountryName) {
        countryName = parCountryName;
    }
}
