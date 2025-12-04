package com.codeit_team01.sb07_hrbank_team01.file.storage;

import com.codeit_team01.sb07_hrbank_team01.file.dto.FileResponseDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "HRBank.storage.type", havingValue = "local")
public class FileLocalStorageImpl implements FileLocalStorage {

    @Value("${HRBank.storage.local.root-path}")
    private String filePath;

    private Path rootPath;

    @PostConstruct
    void init() {
        this.rootPath = Paths.get(filePath).toAbsolutePath();

        if (!Files.exists(this.rootPath)) {
            try {
                Files.createDirectories(this.rootPath);
            } catch (IOException e) {
                // 알맞은 예외 설정 후 변경예정
                throw new RuntimeException("디렉토리 생성 실패", e);
            }
        }
    }

    private Path reslovePath(Long id) {
        return this.rootPath.resolve(id.toString());
    }

    @Override
    public void put(Long id, byte[] bytes) {
        Path path = reslovePath(id);

        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            throw new RuntimeException("파일 쓰기 실패", e);
        }
    }



    public InputStream get(Long id) {
        Path path = reslovePath(id);

        try {
            if (!Files.exists(path)) {
                throw new RuntimeException("파일을 찾을 수 없음");
            }
            return new FileInputStream(path.toFile());
        } catch (IOException e) {
            throw new RuntimeException("파일 읽기 실패", e);
        }
    }

    public ResponseEntity<Resource> download(FileResponseDto fileResponseDto) {
        InputStream fileInputStream = get(fileResponseDto.id());
        InputStreamResource resource = new InputStreamResource(fileInputStream);
        // 테스트용
        File file = reslovePath(fileResponseDto.id()).toFile();
        long realSize = file.length();
        //
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(fileResponseDto.name(), StandardCharsets.UTF_8)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);
        // headers.setContentLength(fileResponseDto.size());
        // 테스트용
        headers.setContentLength(realSize);
        headers.setContentType(MediaType.parseMediaType(fileResponseDto.type()));

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
    @Override
    public Writer getWriter(String filePath) throws IOException {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        return new FileWriter(file);
    }

    @Override
    public long size(Path fullPath) throws IOException {
        return Files.size(fullPath);
    }
}
