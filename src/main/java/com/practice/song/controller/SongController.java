package com.practice.song.controller;

import com.practice.song.entity.Song;
import com.practice.song.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class SongController {
    @Autowired
    private SongService service;
    private final int PEEKMAX = 5; // 何曲後まで格納するか
    private List<Song> songBox;

    @RequestMapping(path = "/song", method = RequestMethod.GET)
    public String song(Model model) {
        List<Song> songs = service.findAll();
        if (service.returnCount() == 0) {
            songBox = StoreSong(PEEKMAX, songs);
        }
        Song nextSong = getNextSong(songs);

        model.addAttribute("Songs", peekQueue());
        model.addAttribute("Next", nextSong);
        return "song";
    }

    /**
     * ランダム5曲のリストを返す
     *
     * @return songBox
     */
    private List<Song> peekQueue() {
        return songBox;
    }

    /**
     * 音楽をシャッフルして格納
     *
     * @param end     格納の回数(未再生の楽曲の数 Max:5)
     * @param allSong データベース内の楽曲
     * @return songBox 次の5曲
     */
    private List<Song> StoreSong(int end, List<Song> allSong) {
        int count = 0;
        List<Song> songBox = new ArrayList<>(); // シャッフル楽曲格納用
        Random rand = new Random();
        while (count < end) { // PEEKMAX分だけsongBoxに曲を格納
            int i = rand.nextInt(allSong.size());
            if (allSong.get(i).getStatus() == 0) {
                if (songBox == null) {  // PEEKMAXがデータベースのデータ数を上回った時用
                    if (songBox.get(songBox.size() - 1).getId() != allSong.get(i).getId()) {
                        songBox.add(allSong.get(i));
                        allSong.get(i).setStatus(1);
                        count++;
                    }
                } else {
                    songBox.add(allSong.get(i));
                    allSong.get(i).setStatus(1);
                    count++;
                }
            }
            if (countStatus(allSong) == 0) { // もし全ての曲を格納した場合はstatusを0に戻す
                resetStatus(allSong);
            }
        }
        return songBox;
    }


    /**
     * 次の曲を決定し、次の5曲(songBox)を更新
     *
     * @param allSong データベースの楽曲
     * @return nextSong
     */
    private Song getNextSong(List<Song> allSong) {
        Random rand = new Random();
        Song nextSong = songBox.get(0);

        if (countStatus(allSong) == 0) { // 全部再生し終えたらstatusをリセット
            resetStatus(allSong);
        }
        while (true) {
            int r = rand.nextInt(allSong.size());

            // 未再生かつ前後で楽曲が被らないように
            if (allSong.get(r).getStatus() == 0 & allSong.get(r).getId() != songBox.get(songBox.size() - 1).getId()) {
                songBox.add(allSong.get(r));
                allSong.get(r).setStatus(1);
                songBox.remove(0);
                break;
            }
        }
        return nextSong;
    }

    /**
     * 未再生データをカウント
     *
     * @param allSong データベース内の楽曲
     * @return count
     */
    private int countStatus(List<Song> allSong) {
        int count = 0;
        for (int i = 0; i < allSong.size(); i++) {
            int status = allSong.get(i).getStatus();
            switch (status) {
                case 0:
                    count++;
                    break;

                case 1:
                    break;
            }
        }
        return count;
    }

    /**
     * テーブル内のStatusを全て0(未再生)に書き換える
     */
    private void resetStatus(List<Song> allSong) {
        for (int i = 0; i < allSong.size(); i++) {
            allSong.get(i).setStatus(0);
        }
    }

}