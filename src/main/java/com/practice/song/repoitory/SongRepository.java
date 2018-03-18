package com.practice.song.repoitory;

import com.practice.song.entity.SongConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import java.util.List;


public interface SongRepository extends JpaRepository<SongConfig, String> {
    public List<SongConfig> findById(int id);
}
