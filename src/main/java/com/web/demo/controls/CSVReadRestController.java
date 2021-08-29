package com.web.demo.controls;

import com.web.demo.dtos.*;
import com.web.demo.services.AsyncService;
import com.web.demo.services.CSVReadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping("csv")
public class CSVReadRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVReadRestController.class);

    private CSVReadService csvReadService;
    private AsyncService asyncService;

    @Autowired
    public void setCSVReadService(CSVReadService csvReadService) {
        this.csvReadService = csvReadService;
    }

    @Autowired
    public void setAsyncService(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping(value = "/states")
    public List<IndiaStatesDTO> getIndiaStates() {
        LOGGER.info("getIndiaStates============");
        CompletableFuture<List<IndiaStatesDTO>> stateFuture =
                supplyAsync(() -> csvReadService.getIndiaStates());
        try {
            return stateFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/readEmp")
    public List<EmployeeDTO> readEmpCSV() {
        CompletableFuture<List<EmployeeDTO>> empFuture =
                supplyAsync(() -> csvReadService.readEmployeeInfo());
        try {
            return empFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/readCrop")
    public List<CropInsuranceDTO> readCropCSV() {
        CompletableFuture<List<CropInsuranceDTO>> cropFuture =
                supplyAsync(() -> csvReadService.readCropDetails());
        try {
            return cropFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/readStudent")
    public List<StudentDTO> readStudentCSV() {
        CompletableFuture<List<StudentDTO>> studentFuture =
                supplyAsync(() -> csvReadService.readStudentInfo());
        try {
            return studentFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/readCountry")
    public List<Countries> readCountryCSV() {
        CompletableFuture<List<Countries>> countriesFuture =
                supplyAsync(() -> csvReadService.readCountriesRegions());
        try {
            return countriesFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @GetMapping(value = "/readSales")
    public List<SalesOrderDTO> readSalesCSV() {
        CompletableFuture<List<SalesOrderDTO>> salesFuture =
                supplyAsync(() -> csvReadService.readSalesOrderDetails());
        try {
            return salesFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/readAll")
    public void readAllCSVFiles() {
        long startTime = System.currentTimeMillis();
        CompletableFuture<List<CropInsuranceDTO>> cropFutureAsync = supplyAsync(() -> asyncService.readCropDetails("csv/crop_insurance.csv"));
        CompletableFuture<List<StudentDTO>> studentFuture = supplyAsync(() -> asyncService.readStudentInfo("csv/StudentInfo.csv"));
        //CompletableFuture<List<EmployeeDTO>> empFuture = supplyAsync(() -> asyncService.readEmployeeInfo("csv/employee.csv"));
        CompletableFuture<List<Countries>> countriesFuture = supplyAsync(() -> asyncService.readCountriesRegions("csv/CountriesRegions.csv"));
        CompletableFuture<List<SalesOrderDTO>> salesFuture = supplyAsync(() -> asyncService.readSalesOrderDetails("csv/100000_Sales_Order.csv"));
        /*CompletableFuture<List<EmployeeDTO>> empFutureTime =
                supplyAsync(() -> asyncService.readEmployeeInfo("csv/employee.csv"))
                        .orTimeout(2, TimeUnit.SECONDS);*/
        CompletableFuture<List<EmployeeDTO>> empFuture =
                supplyAsync(() -> asyncService.readEmployeeInfo("csv/employee.csv"))
                        .completeOnTimeout(new ArrayList<>(), 1, TimeUnit.SECONDS);
        CompletableFuture.allOf(cropFutureAsync, studentFuture, empFuture, countriesFuture, salesFuture);
        try {
            List<CropInsuranceDTO> cropListAsync = cropFutureAsync.get();
            List<StudentDTO> stdList = studentFuture.get();
            List<EmployeeDTO> empList = empFuture.get();
            List<Countries> contList = countriesFuture.get();
            List<SalesOrderDTO> saleList = salesFuture.get();
            System.out.println("CropSize==" + cropListAsync.size() + "==StudentSize==" + stdList.size() + "==EmpSize==" + empList.size() +
                    "==CountrySize==" + contList.size() + "===SalesSize===" + saleList.size());
            long endTime = System.currentTimeMillis();
            System.out.println("===========================================");
            System.out.println("ASync Total Time Taken==" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
