package com.practice.song.controller;

import com.practice.song.entity.SongConfig;
import com.practice.song.repoitory.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;


@Controller
public class SongController {
    @Autowired
    private SongRepository repository;

    @RequestMapping(value = "/song", method = RequestMethod.POST)
    public String find(Model model, @RequestParam("find") int id){
        SongConfig song = repository.getOne(id);
        model.addAttribute("Songs", song);
        return "test";
    }

}