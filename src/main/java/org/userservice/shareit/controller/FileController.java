package org.userservice.shareit.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class FileController {

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
