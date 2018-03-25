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

    public List<Song> findAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }

    public void updateData() {
        repository.flush();
    }

}
