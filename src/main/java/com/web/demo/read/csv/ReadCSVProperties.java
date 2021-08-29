package com.web.demo.read.csv;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "csv.read")
@Configuration
public class ReadCSVProperties {

    private String gitHubUrl;
    private String windPrefix;
    private String linuxPrefix;
    private String macPrefix;
    private String empSuffix;

    public String getMacPrefix() {
        return macPrefix;
    }

    public void setMacPrefix(String macPrefix) {
        this.macPrefix = macPrefix;
    }
    public String getGitHubUrl() {
        return gitHubUrl;
    }

    public void setGitHubUrl(String gitHubUrl) {
        this.gitHubUrl = gitHubUrl;
    }

    public String getWindPrefix() {
        return windPrefix;
    }

    public void setWindPrefix(String windPrefix) {
        this.windPrefix = windPrefix;
    }

    public String getLinuxPrefix() {
        return linuxPrefix;
    }

    public void setLinuxPrefix(String linuxPrefix) {
        this.linuxPrefix = linuxPrefix;
    }

    public String getEmpSuffix() {
        return empSuffix;
    }

    public void setEmpSuffix(String empSuffix) {
        this.empSuffix = empSuffix;
    }
}
