package com.lil.pretty.domain.common.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    @GetMapping("/download/**")
    public ResponseEntity<ByteArrayResource> downloadFile(HttpServletRequest request) throws Exception {
        // URI 전체 경로 추출
        String requestUri = request.getRequestURI(); // /api/file/download/mealRec/테이블_정의서_...
        String encodedPath = requestUri.substring(requestUri.indexOf("/download/") + 10);

        // URL 디코딩 (한글, 공백 등 포함)
        String fullPath = URLDecoder.decode(encodedPath, StandardCharsets.UTF_8);
        log.info("📂 요청 파일 경로: {}", fullPath);

        // 파일 읽기
        byte[] data = fileService.downloadFile(fullPath);
        ByteArrayResource resource = new ByteArrayResource(data);

        // 파일명 추출
        Path path = Paths.get(fullPath);
        String fileName = path.getFileName().toString();

        //파일명 인코딩 (한글 포함 시 깨짐 방지)
        String encodedFilename = URLEncoder.encode(fileName, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        // 응답
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFilename)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(data.length)
                .body(resource);
    }
}
