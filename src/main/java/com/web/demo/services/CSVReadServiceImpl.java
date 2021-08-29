package com.web.demo.services;

import com.web.demo.async.*;
import com.web.demo.dtos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.web.demo.utils.ExecutorServiceUtil.getTheExecutorService;
import static java.util.concurrent.CompletableFuture.supplyAsync;

@Service
public class CSVReadServiceImpl implements CSVReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVReadServiceImpl.class);

    @Override
    public List<IndiaStatesDTO> getIndiaStates() {
        LOGGER.info("getIndiaStates====");
        CompletableFuture<List<IndiaStatesDTO>> empFuture = supplyAsync(new ReadIndiaStatesSupplier(), getTheExecutorService());
        List<IndiaStatesDTO> statesList;
        try {
            statesList = empFuture.get();
            statesList = Optional.ofNullable(statesList)
                    .orElseGet(Collections::emptyList)
                    .parallelStream()
                    .filter(Objects::nonNull)
                    .filter(f -> !f.getStateName().contains("&"))
                    .filter(f -> f.getStateName() != null && f.getStateName().equalsIgnoreCase("HARYANA"))
                    .collect(Collectors.toList());
            return statesList;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<EmployeeDTO> readEmployeeInfo() {
        CompletableFuture<List<EmployeeDTO>> empFuture = supplyAsync(new ReadEmployeeSupplier());
        try {
            TimeUnit.SECONDS.sleep(10);
            return empFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CropInsuranceDTO> readCropDetails() {
        CompletableFuture<List<CropInsuranceDTO>> cropFuture = supplyAsync(new ReadCropInsuranceSupplier());
        try {
            return cropFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StudentDTO> readStudentInfo() {
        CompletableFuture<List<StudentDTO>> cropFuture = supplyAsync(new ReadStudentSupplier());
        try {
            return cropFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Countries> readCountriesRegions() {
        CompletableFuture<List<Countries>> cropFuture = supplyAsync(new ReadCountriesSupplier());
        try {
            return cropFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SalesOrderDTO> readSalesOrderDetails() {
        CompletableFuture<List<SalesOrderDTO>> cropFuture = supplyAsync(new ReadSalesOrderSupplier());
        try {
            return cropFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*@Override
    public List<CSVUser> readCSVToMode() {
        Iterator<CSVUser> csvUserIterator = null;
        List<CSVUser> usersList = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(IConstants.USERS_WITH_HEADER));) {
            CsvToBean<CSVUser> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVUser.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            csvUserIterator = csvToBean.iterator();

            while (csvUserIterator.hasNext()) {
                CSVUser csvUser = csvUserIterator.next();
                LOGGER.info(String.format("Name : %1$s", csvUser.getName()));
                LOGGER.info(String.format("Email : %1$s", csvUser.getEmail()));
                LOGGER.info(String.format("PhoneNo : %1$s", csvUser.getPhoneNo()));
                LOGGER.info(String.format("Country : %1$s", csvUser.getCountry()));
                LOGGER.info("==========================");
                usersList.add(new CSVUser(csvUser.getName(), csvUser.getEmail(), csvUser.getPhoneNo(), csvUser.getCountry()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersList;
    }*/

}
