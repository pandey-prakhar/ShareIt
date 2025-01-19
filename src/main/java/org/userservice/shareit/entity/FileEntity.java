package org.userservice.shareit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fileName;
    private String uploadedBy;
    private LocalDateTime uploadedTime;
    private LocalDateTime expiryTime;

    @Lob
    @Column(name="file_data", columnDefinition = "LONGBLOB")
    private byte[] fileData;
}
