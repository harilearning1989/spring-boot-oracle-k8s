package com.web.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.web.demo.dtos.CropInsuranceDTO;
import com.web.demo.utils.IDemoUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropServiceImpl implements CropService {

    @Override
    public List<CropInsuranceDTO> readCropDetails() {

        List<CropInsuranceDTO> countryRegion = null;
        try {
            String fixture = IDemoUtils.readResource("crops.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            countryRegion = objectMapper.readValue(fixture,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, CropInsuranceDTO.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countryRegion;
    }
}
