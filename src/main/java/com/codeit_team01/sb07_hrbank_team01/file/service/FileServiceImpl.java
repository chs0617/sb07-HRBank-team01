package com.codeit_team01.sb07_hrbank_team01.file.service;

import com.codeit_team01.sb07_hrbank_team01.file.dto.FileCreateRequestDto;
import com.codeit_team01.sb07_hrbank_team01.file.dto.FileResponseDto;
import com.codeit_team01.sb07_hrbank_team01.file.entity.File;
import com.codeit_team01.sb07_hrbank_team01.file.mapper.FileMapper;
import com.codeit_team01.sb07_hrbank_team01.file.repository.FileRepository;
import com.codeit_team01.sb07_hrbank_team01.file.storage.FileLocalStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final FileLocalStorage fileLocalStorage;
    private final FileMapper fileMapper;

    @Override
    @Transactional
    public FileResponseDto createFile(FileCreateRequestDto fileCreateRequestDto) {
        File file = File.builder()
                .name(fileCreateRequestDto.name())
                .type(fileCreateRequestDto.type())
                .size((long) fileCreateRequestDto.bytes().length)
                .build();
        fileRepository.save(file);
        fileLocalStorage.put(file.getId(), fileCreateRequestDto.bytes());
        return fileMapper.toDto(file);
    }

    @Override
    @Transactional(readOnly = true)
    public FileResponseDto findById(long id) {
        File file = fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found!"));
        return fileMapper.toDto(file);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        fileRepository.deleteById(id);
    }
}
