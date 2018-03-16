package com.practice.song.repoitory;

import com.practice.song.entity.SongConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;


public interface SongRepository extends Repository<SongConfig, String> {
    Page<SongConfig> findAll(Pageable pageable);
}
