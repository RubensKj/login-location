package com.rubenskj.security.loginlocation.model;

public class Location {

    private String ip;
    private String country;
    private String city;

    public Location() {
    }

    public Location(String ip, String country, String city) {
        this.ip = ip;
        this.country = country;
        this.city = city;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
