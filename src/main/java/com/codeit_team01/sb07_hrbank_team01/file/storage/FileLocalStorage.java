package com.codeit_team01.sb07_hrbank_team01.file.storage;

import com.codeit_team01.sb07_hrbank_team01.file.dto.FileResponseDto;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.InputStream;

public interface FileLocalStorage {

    void put(Long id, byte[] bytes);

    InputStream get(Long id);

    ResponseEntity<Resource> download(FileResponseDto fileResponseDto);
}
