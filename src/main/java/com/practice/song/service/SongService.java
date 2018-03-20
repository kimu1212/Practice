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
    //TODO: 各メソッドの定義は後々
//    public final int PEEKMAX = 5;
//    interface SuffleEngine{
//        void setSongs(Song[] songs);
//        Songs[] getNextSong();
//        Song[] peekQueue();
//    }
//
}
