package com.web.demo.async;

import com.opencsv.bean.CsvToBeanBuilder;
import com.web.demo.download.DownloadGitHubFiles;
import com.web.demo.dtos.EmployeeDTO;

import java.io.FileReader;
import java.util.List;
import java.util.function.Supplier;

public class ReadEmployeeSupplier implements Supplier<List<EmployeeDTO>> {

    @Override
    public List<EmployeeDTO> get() {
        List<EmployeeDTO> listCrop = null;
        try {
            String fileName = DownloadGitHubFiles.downloadFile("csv/employee.csv");
            listCrop = new CsvToBeanBuilder(new FileReader(fileName))
                    .withType(EmployeeDTO.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCrop;
    }
}
