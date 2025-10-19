package com.lil.pretty.domain.common.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import com.lil.pretty.domain.common.service.FileService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/file/")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    // 📤 파일 업로드
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName = fileService.uploadFile(file);
        return ResponseEntity.ok(fileName);
    }

    /**
     * 📥 파일 다운로드
     * 예시 요청: GET /api/file/download/mealRec/테이블_정의서_20251007122110.xlsx
     */
    @GetMapping("download/{folder}/{fileName:.+}")
    public ResponseEntity<ByteArrayResource> downloadFile(
            @PathVariable String folder,
            @PathVariable String fileName) throws IOException {
        log.info("폴더: {}, 파일명: {}", folder, fileName);

        // 실제 파일 경로
        Path filePath = Paths.get("upload", folder, fileName);
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("파일을 찾을 수 없습니다: " + filePath);
        }

        // 파일 읽기
        byte[] data = Files.readAllBytes(filePath);
        ByteArrayResource resource = new ByteArrayResource(data);

        // 파일명 브라우저 호환 인코딩
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        String contentDisposition = "attachment; filename*=UTF-8''" + encodedFileName;

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(data.length)
                .body(resource);
    }
}
