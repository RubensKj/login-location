package com.rubenskj.security.loginlocation.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "loginlocat.path")
public class DbMaxMindProperty {

    private String dblocation;

    public String getDblocation() {
        return dblocation;
    }

    public void setDblocation(String dblocation) {
        this.dblocation = dblocation;
    }
}
