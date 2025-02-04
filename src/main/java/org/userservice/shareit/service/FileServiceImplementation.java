package org.userservice.shareit.service;


import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.userservice.shareit.entity.FileEntity;
import org.userservice.shareit.exceptions.FileNotFoundException;
import org.userservice.shareit.model.FileModel;
import org.userservice.shareit.repository.FileRepository;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
        return ResponseEntity.ok().body(FileModel.from(entity));
    }

    @Override
    public ResponseEntity<?> shareFile(Integer id) throws FileNotFoundException {
        Optional<FileEntity> optionalFile = fileRepository.findById(id);
        if(optionalFile.isEmpty()){
            throw new FileNotFoundException("File with given Id is not found.");
        }
       return ResponseEntity.ok().body(FileModel.from(optionalFile.get()));
    }

    @Override
    public ResponseEntity<?> deleteFile(Integer id) {
        Optional<FileEntity> optionalFile = fileRepository.findById(id);
        if(optionalFile.isEmpty()){
            throw new FileNotFoundException("File with given Id is not found to delete.");
        }
        fileRepository.delete(optionalFile.get());
        return ResponseEntity.ok().body("File with id " + id + " has been deleted");
    }

    @Override
    public ResponseEntity<?> getFile(int id) {
        Optional<FileEntity> optionalFile = fileRepository.findById(id);
        if(optionalFile.isPresent()){
            FileEntity fileEntity = optionalFile.get();
            FileModel fileModel = FileModel.from(fileEntity);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileModel.getFileName() + "\"").body(fileModel.getFileData());
        }
        throw new FileNotFoundException("File with given Id is not found to delete.");
    }

    @Override
    @Scheduled(cron = "0 0 * * * *") //
    public void deleteExpiredFile() {
        List<FileEntity> files = fileRepository.findByExpiryTimeBefore(LocalDateTime.now());
        if(files.size() > 0){
            for(FileEntity fileEntity : files){
                fileRepository.delete(fileEntity);
            }
            System.out.println("Expired Files deleted");
        }
        else{
            System.out.println("No file Expired to be deleted");
        }
    }
}
