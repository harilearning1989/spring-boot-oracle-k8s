package com.web.demo.dtos;

public class DistrictsHospitalsDTO extends DistrictsDTO {

    private String name;
    private String helpDeskNumber;
    private String nodalOfficeNumber;
    private String aarogyasriStatus;

    public String getHelpDeskNumber() {
        return helpDeskNumber;
    }

    public void setHelpDeskNumber(String helpDeskNumber) {
        this.helpDeskNumber = helpDeskNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNodalOfficeNumber() {
        return nodalOfficeNumber;
    }

    public void setNodalOfficeNumber(String nodalOfficeNumber) {
        this.nodalOfficeNumber = nodalOfficeNumber;
    }

    public String getAarogyasriStatus() {
        return aarogyasriStatus;
    }

    public void setAarogyasriStatus(String aarogyasriStatus) {
        this.aarogyasriStatus = aarogyasriStatus;
    }
}
