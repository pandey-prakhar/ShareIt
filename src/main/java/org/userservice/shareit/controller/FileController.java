package org.userservice.shareit.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.userservice.shareit.service.FileService;

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
