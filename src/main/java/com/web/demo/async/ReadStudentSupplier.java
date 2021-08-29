package com.web.demo.async;

import com.opencsv.bean.CsvToBeanBuilder;
import com.web.demo.download.DownloadGitHubFiles;
import com.web.demo.dtos.StudentDTO;

import java.io.FileReader;
import java.util.List;
import java.util.function.Supplier;

public class ReadStudentSupplier implements Supplier<List<StudentDTO>> {
    @Override
    public List<StudentDTO> get() {
        List<StudentDTO> listCrop = null;
        try {
            String fileName = DownloadGitHubFiles.downloadFile("csv/StudentInfo.csv");
            listCrop = new CsvToBeanBuilder(new FileReader(fileName))
                    .withType(StudentDTO.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCrop;
    }
}
