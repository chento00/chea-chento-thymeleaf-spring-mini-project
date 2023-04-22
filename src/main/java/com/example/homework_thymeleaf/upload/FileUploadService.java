package com.example.homework_thymeleaf.upload;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    FileUpload uploadSingle(MultipartFile file);
}
