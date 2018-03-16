package com.practice.song.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class OutputController {
    @RequestMapping("/home")
    public String home(Model model){
        model.addAttribute("music", "trackNum");
        return "test";
    }
}
