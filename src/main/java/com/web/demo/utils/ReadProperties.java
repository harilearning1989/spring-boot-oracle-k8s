package com.web.demo.utils;

import com.web.demo.read.csv.ReadCSVProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReadProperties {

    private static ReadCSVProperties readCSVProperties;

    @Autowired
    private void setReadCSVProperties(ReadCSVProperties readCSVProperties) {
        this.readCSVProperties = readCSVProperties;
    }

    public static String getFilePrefix() {
        if (CommonUtils.isWindows()) {
            return readCSVProperties.getWindPrefix();
        } else if (CommonUtils.isLinux()) {
            return readCSVProperties.getLinuxPrefix();
        } else if (CommonUtils.isMac()) {
            return readCSVProperties.getMacPrefix();
        }
        return readCSVProperties.getWindPrefix();
    }

    public static String getGitHubUrl() {
        return readCSVProperties.getGitHubUrl();
    }
}
