package org.userservice.shareit.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.userservice.shareit.model.FileModel;
import org.userservice.shareit.service.FileService;

import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/files")
public class FileController {

    FileService fileService;

    FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/home")
    public String index(Model model){
        model.addAttribute("files", fileService.getAll());
        return "list-files";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("uploadedBy") String uploadedBY) throws IOException {
        fileService.uploadFile(file, uploadedBY);
        return "redirect:/files/home";
    }

    @GetMapping("/share/{id}")
    public String shareFile(@PathVariable("id") Integer id, Model model) throws FileNotFoundException {
        ResponseEntity<?> fileModel= fileService.shareFile(id);
        if(fileModel.hasBody()){
            String currentUrl= ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
            model.addAttribute("shareUrl", currentUrl);
            model.addAttribute("file", fileModel.getBody());
            return "share-file";
        }
        return "redirect:/files/home";
    }

    @PostMapping("/delete/{id}")
    public String deleteFile(@PathVariable("id") Integer id){
        ResponseEntity<?> responseEntity= fileService.deleteFile(id);
        if(responseEntity.hasBody()){
            return "redirect:/files/home";
        }
        return "redirect:/files/home";
    }

    @GetMapping("/login")
    public String login(){
        return "home";
    }

    @GetMapping("/list")
    public String files(){
        return "list-files";
    }

    @GetMapping("/share")
    public String share(){
        return "share-file";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Integer id){
        return fileService.getFile(id);
    }



}
