package com.web.demo.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistrictsDTO {

    private int id;
    private String district;
    private int hospitals;
    private int icuBedsTotal;
    private int icuBedsOccupaid;
    private int icuBedsAvailable;
    private int generalBedsTotal;
    private int generalBedsOccupied;
    private int generalBedsAvailable;
    private int ventilatorTotal;
    private int ventilatorOccupied;
    private int ventilatorAvailable;

    public int getIcuBedsTotal() {
        return icuBedsTotal;
    }

    public void setIcuBedsTotal(int icuBedsTotal) {
        this.icuBedsTotal = icuBedsTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getHospitals() {
        return hospitals;
    }

    public void setHospitals(int hospitals) {
        this.hospitals = hospitals;
    }

    public int getIcuBedsOccupaid() {
        return icuBedsOccupaid;
    }

    public void setIcuBedsOccupaid(int icuBedsOccupaid) {
        this.icuBedsOccupaid = icuBedsOccupaid;
    }

    public int getIcuBedsAvailable() {
        return icuBedsAvailable;
    }

    public void setIcuBedsAvailable(int icuBedsAvailable) {
        this.icuBedsAvailable = icuBedsAvailable;
    }

    public int getGeneralBedsTotal() {
        return generalBedsTotal;
    }

    public void setGeneralBedsTotal(int generalBedsTotal) {
        this.generalBedsTotal = generalBedsTotal;
    }

    public int getGeneralBedsOccupied() {
        return generalBedsOccupied;
    }

    public void setGeneralBedsOccupied(int generalBedsOccupied) {
        this.generalBedsOccupied = generalBedsOccupied;
    }

    public int getGeneralBedsAvailable() {
        return generalBedsAvailable;
    }

    public void setGeneralBedsAvailable(int generalBedsAvailable) {
        this.generalBedsAvailable = generalBedsAvailable;
    }

    public int getVentilatorTotal() {
        return ventilatorTotal;
    }

    public void setVentilatorTotal(int ventilatorTotal) {
        this.ventilatorTotal = ventilatorTotal;
    }

    public int getVentilatorOccupied() {
        return ventilatorOccupied;
    }

    public void setVentilatorOccupied(int ventilatorOccupied) {
        this.ventilatorOccupied = ventilatorOccupied;
    }

    public int getVentilatorAvailable() {
        return ventilatorAvailable;
    }

    public void setVentilatorAvailable(int ventilatorAvailable) {
        this.ventilatorAvailable = ventilatorAvailable;
    }
}
