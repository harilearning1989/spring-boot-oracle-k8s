package com.web.demo.async;

import com.opencsv.bean.CsvToBeanBuilder;
import com.web.demo.download.DownloadGitHubFiles;
import com.web.demo.dtos.SalesOrderDTO;

import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ReadSalesOrderSupplier implements Supplier<List<SalesOrderDTO>> {
    @Override
    public List<SalesOrderDTO> get() {
        List<SalesOrderDTO> listCrop = null;
        try {
            String fileName = DownloadGitHubFiles.downloadFile("csv/SalesOrder.csv");
            listCrop = new CsvToBeanBuilder(new FileReader(fileName))
                    .withType(SalesOrderDTO.class)
                    .build()
                    .parse();
            listCrop = Optional.ofNullable(listCrop)
                    .orElseGet(Collections::emptyList)
                    .parallelStream()
                    .filter(Objects::nonNull)
                    .filter(f -> f.getOrderPriority().equalsIgnoreCase("h"))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCrop;
    }
}
