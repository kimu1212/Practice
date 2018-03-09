package com.practice.song.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutputController {
    @RequestMapping
    public String getHello(){
        return "hello";
    }
}
