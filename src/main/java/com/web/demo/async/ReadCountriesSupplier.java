package com.web.demo.async;

import com.opencsv.bean.CsvToBeanBuilder;
import com.web.demo.download.DownloadGitHubFiles;
import com.web.demo.dtos.Countries;

import java.io.FileReader;
import java.util.List;
import java.util.function.Supplier;

public class ReadCountriesSupplier implements Supplier<List<Countries>> {
    @Override
    public List<Countries> get() {
        List<Countries> listCrop = null;
        try {
            String fileName = DownloadGitHubFiles.downloadFile("csv/CountriesRegions.csv");
            listCrop = new CsvToBeanBuilder(new FileReader(fileName))
                    .withType(Countries.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCrop;
    }
}
