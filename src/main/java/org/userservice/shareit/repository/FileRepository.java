package org.userservice.shareit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.userservice.shareit.entity.FileEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Integer>{

    List<FileEntity> findAllByExpiryTimeBefore(LocalDateTime now);


}
