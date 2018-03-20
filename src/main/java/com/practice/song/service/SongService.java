package com.practice.song.service;

import com.practice.song.entity.SongConfig;
import com.practice.song.repoitory.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    @Autowired
    SongRepository repository;

    public SongConfig search(Integer id){

        return song;
    }

}
