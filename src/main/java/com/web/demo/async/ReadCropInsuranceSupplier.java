package com.web.demo.async;

import com.opencsv.bean.CsvToBeanBuilder;
import com.web.demo.download.DownloadGitHubFiles;
import com.web.demo.dtos.CropInsuranceDTO;

import java.io.FileReader;
import java.util.List;
import java.util.function.Supplier;

public class ReadCropInsuranceSupplier implements Supplier<List<CropInsuranceDTO>> {

    @Override
    public List<CropInsuranceDTO> get() {
        List<CropInsuranceDTO> listCrop = null;
        try {
            String fileName = DownloadGitHubFiles.downloadFile("csv/crop_insurance.csv");
            listCrop = new CsvToBeanBuilder(new FileReader(fileName))
                    .withType(CropInsuranceDTO.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCrop;
    }
}
