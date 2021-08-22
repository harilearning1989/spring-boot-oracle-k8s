package com.web.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.web.demo.dtos.SalesOrderDTO;
import com.web.demo.utils.IDemoUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

    @Override
    public List<SalesOrderDTO> readSalesDetails() {

        List<SalesOrderDTO> countryRegion = null;
        try {
            String fixture = IDemoUtils.readResource("sales.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            countryRegion = objectMapper.readValue(fixture,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, SalesOrderDTO.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countryRegion;
    }

}
