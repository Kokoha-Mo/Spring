package com.example.spring1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ReadConfig {
    @Value("${file.upload.dir}")
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }
}
