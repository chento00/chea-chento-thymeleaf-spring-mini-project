package com.example.homework_thymeleaf.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class FileUploadServiceImp implements FileUploadService{
    @Value("${file.server.path}")
    private String fileServerPath;
    @Override
    public FileUpload uploadSingle(MultipartFile file) {
        String uuid=UUID.randomUUID().toString();
        int lastIndex=file.getOriginalFilename().lastIndexOf(".");
        System.out.println(file.getOriginalFilename());
        String ext=file.getOriginalFilename().substring(lastIndex+1);
        System.out.println(ext);
        String newFileName=String.format("%s%s%s",uuid,".",ext);
        Path paths= Paths.get(fileServerPath+newFileName);
        try {
            Files.copy(file.getInputStream(),paths);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(newFileName);
        return new FileUpload(newFileName,true);
    }
}
