package org.userservice.shareit.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.userservice.shareit.model.FileModel;

import java.io.IOException;
import java.util.List;


public interface FileService {
    public List<FileModel> getAll();
    public ResponseEntity<?> uploadFile(MultipartFile file,String uploadedBy) throws IOException;
    public ResponseEntity<?> shareFile(Integer id);
    public ResponseEntity<?> deleteFile(Integer id);
}
