package org.userservice.shareit.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.userservice.shareit.entity.FileEntity;
import org.userservice.shareit.model.FileModel;
import org.userservice.shareit.repository.FileRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImplementation  implements FileService {

    private FileRepository fileRepository;

    FileServiceImplementation(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public List<FileModel> getAll() {
        List<FileEntity> files = fileRepository.findAll();
        return files.stream()
                .map(FileModel::from)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> uploadFile(MultipartFile file, String uploadedBy) throws IOException {
        FileEntity entity= new FileEntity();
        entity.setFileName(file.getOriginalFilename());
        entity.setUploadedBy(uploadedBy);
        entity.setUploadedTime(LocalDateTime.now());
        entity.setExpiryTime(LocalDateTime.now().plusDays(1));
        entity.setFileData(file.getBytes());
        fileRepository.save(entity);
        return ResponseEntity.ok(FileModel.from(entity));
    }

    @Override
    public ResponseEntity<?> shareFile(Integer id) {
       return null;
    }

    @Override
    public ResponseEntity<?> deleteFile(Integer id) {
        return null;
    }
}
