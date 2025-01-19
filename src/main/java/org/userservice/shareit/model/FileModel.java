package org.userservice.shareit.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.userservice.shareit.entity.FileEntity;

import java.time.LocalDateTime;


@Getter
@Setter
public class FileModel {
    private Integer id;
    private String fileName;
    private String uploadedBy;
    private LocalDateTime uploadedTime;
    private LocalDateTime expiryTime;
    private byte[] fileData;

    public static FileModel from(FileEntity entity) {
        FileModel fileModel = new FileModel();
        fileModel.setId(entity.getId());
        fileModel.setFileName(entity.getFileName());
        fileModel.setUploadedBy(entity.getUploadedBy());
        fileModel.setUploadedTime(entity.getUploadedTime());
        fileModel.setFileData(entity.getFileData());
        return fileModel;
    }
}
