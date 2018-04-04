package com.practice.song.controller;

import com.practice.song.entity.Song;
import com.practice.song.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class SongController {
    @Autowired
    private SongService service;
    private List<Song> songBox; /* 再生リスト */
    private Integer playStyle = 0; /* ソート=0 シャッフル=1 お気に入り=2 */
    private Integer playNum = 0;

    @RequestMapping(path = "/song", method = RequestMethod.GET)
    public String top() {
        return "top";
    }

    @RequestMapping(path = "/song", method = RequestMethod.GET, params = "Sort")
    public String sortSong(Model model) {
        playStyle = 0;
        List<Song> songs = service.findAll();
        songBox = service.getSortSongs(songs);
        model.addAttribute("Style", "通常再生");
        model.addAttribute("Songs", songBox);
        return "song";
    }

    @RequestMapping(path = "/song", method = RequestMethod.GET, params = "Shuffle")
    public String song(Model model) {
        playStyle = 1;
        List<Song> songs = service.findAll();
        service.resetStatus(songs);
        songBox = service.peekQueue(songs);
        model.addAttribute("Style", "シャッフル再生");
        model.addAttribute("Songs", songBox);
        return "song";
    }


    @RequestMapping(path = "/song", method = RequestMethod.GET, params = "Next")
    public String Next(Model model) {
        List<Song> songs = service.findAll();
        if (playStyle == 0) {
            songBox = service.getNextSortSong(songs, songBox);
            model.addAttribute("Style", "通常再生");
        } else if (playStyle == 1) {
            songBox = service.getNextSong(songs, songBox);
            model.addAttribute("Style", "シャッフル再生");
        }
        model.addAttribute("Songs", songBox);
        return "song";
    }
}