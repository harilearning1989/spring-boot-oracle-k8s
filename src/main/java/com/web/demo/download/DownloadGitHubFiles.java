package com.web.demo.download;

import com.web.demo.utils.ReadProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Component
public class DownloadGitHubFiles {

    public static String downloadFile(String fileSuffix) throws Exception {
        String fileName = ReadProperties.getFilePrefix() + fileSuffix;
        File file = new File(fileName);
        try {
            if (file.exists()) {
                if (file.isDirectory())
                    throw new IOException("File '" + file + "' is a directory");
                if (!file.canWrite())
                    throw new IOException("File '" + file + "' cannot be written");
                System.out.println("File '" + file + "' already downloaded!");
            } else {
                File parent = file.getParentFile();
                if ((parent != null) && (!parent.exists()) && (!parent.mkdirs())) {
                    throw new IOException("File '" + file + "' could not be created");
                }
                String sUrl = ReadProperties.getGitHubUrl() + fileSuffix;
                URL url = new URL(sUrl);
                FileOutputStream output = new FileOutputStream(file);
                InputStream input = url.openStream();
                byte[] buffer = new byte[4096];
                int n;
                while (-1 != (n = input.read(buffer))) {
                    output.write(buffer, 0, n);
                }
                input.close();
                output.close();
                System.out.println("File '" + file + "' downloaded successfully!");
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        return fileName;
    }
}
