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
@RequestMapping
public class FileController {

    FileService fileService;

    FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("files", fileService.getAll());
        return "list-files";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("uploadedBy") String uploadedBY) throws IOException {
        fileService.uploadFile(file, uploadedBY);
        return "redirect:/";
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
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable("id") Integer id){
        return fileService.deleteFile(id);
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

}
