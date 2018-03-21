package com.practice.song.controller;

import com.practice.song.entity.Song;
import com.practice.song.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Random;


@Controller
public class SongController {
    @Autowired
    private SongService service;

    @RequestMapping()
    public String song(Model model){
        List<Song> songs = service.findAll();
        Random rand = new Random();
        model.addAttribute("Songs", service.getNextSong());
        return "song";
    }

}