package com.lil.pretty.domain.common.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lil.pretty.config.FileStorageConfig;
import com.lil.pretty.domain.date.repository.DateMasterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {
	
	private final FileStorageConfig config;
	
    public String uploadFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(config.getUploadDir());
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        return fileName; // 업로드된 파일명 반환
    }
    

    public byte[] downloadFile(String fileName) throws IOException {
        Path filePath = Paths.get(config.getUploadDir(), fileName);
        return Files.readAllBytes(filePath);
    }
    
}
