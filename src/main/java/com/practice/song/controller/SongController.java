package com.practice.song.controller;

import com.practice.song.entity.SongConfig;
import com.practice.song.repoitory.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod

import java.util.List;


@Controller
public class SongController {
    @Autowired
    private SongRepository repository;

    @RequestMapping(path = "/song", method = RequestMethod.GET)
    @Transactional
    public List<SongConfig> get() {
        return repository.findAll();
    }

    @RequestMapping(path = "/song/id", method = RequestMethod.GET)
    public List<SongConfig> show(Model model, @PathVariable("id") int id) {
        return repository.findById(id);
    }
}