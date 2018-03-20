package com.practice.song.service;

import com.practice.song.entity.Song;
import com.practice.song.repoitory.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Song> findAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }
}
