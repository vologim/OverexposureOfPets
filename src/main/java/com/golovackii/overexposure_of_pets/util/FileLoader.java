package com.golovackii.overexposure_of_pets.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

@Component
public class FileLoader {
    @Value("${file.path}")
    private String pathFolder;

    public String uploadFile(String storage, MultipartFile multipartFile) {

        String filePath = pathFolder + storage;

        try {
            Files.createDirectories(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileAddress = filePath + "/"+ Calendar.getInstance().getTimeInMillis() + multipartFile.getOriginalFilename();

        File file = new File(fileAddress);
        try (OutputStream outputStream = new FileOutputStream(file)) {
            byte[] bytes = multipartFile.getBytes();
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileAddress;
    }

    public void deleteFile(String path) {
        try {
            Files.delete(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
