package com.web.demo.controls;

import com.web.demo.dtos.SalesOrderDTO;
import com.web.demo.services.SalesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping("sales")
public class SalesRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalesRestController.class);

    SalesService salesService;
    @Autowired
    public void setSalesService(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping(value = "/readSales")
    public ResponseEntity<List<SalesOrderDTO>> readCropCSV() {
        CompletableFuture<List<SalesOrderDTO>> cropFuture =
                supplyAsync(() -> salesService.readSalesDetails());
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cropFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
