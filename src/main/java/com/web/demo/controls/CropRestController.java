package com.web.demo.controls;

import com.web.demo.dtos.CropInsuranceDTO;
import com.web.demo.services.CropService;
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
@RequestMapping("crops")
public class CropRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CropRestController.class);

    CropService cropService;

    @Autowired
    public void setCropService(CropService cropService) {
        this.cropService = cropService;
    }

    @GetMapping(value = "/readCrop")
    public ResponseEntity<List<CropInsuranceDTO>> readCropCSV() {
        CompletableFuture<List<CropInsuranceDTO>> cropFuture =
                supplyAsync(() -> cropService.readCropDetails());
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cropFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
