package com.web.demo.services;

import com.web.demo.dtos.*;

import java.util.List;

public interface CSVReadService {

    List<EmployeeDTO> readEmployeeInfo();

    List<CropInsuranceDTO> readCropDetails();

    List<StudentDTO> readStudentInfo();

    List<Countries> readCountriesRegions();

    List<SalesOrderDTO> readSalesOrderDetails();

    List<IndiaStatesDTO> getIndiaStates();
}
