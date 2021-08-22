package com.web.demo.services;

import com.web.demo.dtos.CropInsuranceDTO;

import java.util.List;

public interface CropService {
    List<CropInsuranceDTO> readCropDetails();
}
