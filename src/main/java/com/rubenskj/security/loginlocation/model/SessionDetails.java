package com.rubenskj.security.loginlocation.model;

public class SessionDetails {

    private String browser;
    private String operationalSystem;
    private String deviceName;

    public SessionDetails() {
    }

    public SessionDetails(String browser, String operationalSystem, String deviceName) {
        this.browser = browser;
        this.operationalSystem = operationalSystem;
        this.deviceName = deviceName;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOperationalSystem() {
        return operationalSystem;
    }

    public void setOperationalSystem(String operationalSystem) {
        this.operationalSystem = operationalSystem;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
