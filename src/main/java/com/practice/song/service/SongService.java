package com.practice.song.service;

import com.practice.song.entity.Song;
import com.practice.song.repoitory.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SongService {

    @Autowired
    SongRepository repository;

    private List<Song> allSong = findAll();
    private List<Song> targetSong = storeTarget();
    private List<Song> songBox = new ArrayList<>(); // シャッフル楽曲格納用

    //TODO: 各メソッドの定義は後々
    public final int PEEKMAX = 5;

    interface ShuffleEngine {
        void setSongs(Song[] songs);

        Song getNextSong();

        List<Song> peekQueue();
    }

    /**
     * ランダム5曲のリストを返す
     *
     * @return songBox
     */
    public Song peekQueue() {
        if (PEEKMAX <= targetSong.size()) {
            songBox = StoreSong(PEEKMAX, targetSong);
        }
        return (Song) songBox;
    }

    /**
     * 音楽をシャッフルして格納
     *
     * @param end        格納の回数(未再生の楽曲の数 Max:5)
     * @param targetSong 未再生音楽のリスト
     * @return songBox
     */
    private List<Song> StoreSong(int end, List<Song> targetSong) {
        int count = 0;
        List<Song> songBox = new ArrayList<>(); // シャッフル楽曲格納用
        Random rand = new Random();
        while (count < end) {
            int i = rand.nextInt(targetSong.size());
            if (targetSong.get(i).getStatus() == 0) {
                songBox.add(targetSong.get(i));
                targetSong.get(i).setStatus(1);
                count++;
            }
        }
        return songBox;
    }

    private List<Song> storeTarget() {
        List<Song> targetSong = new ArrayList<>();
        resetStatus();
        for (int c = 0; c < allSong.size(); c++) {
            int status = allSong.get(c).getStatus();
            switch (status) {
                case 0:
                    targetSong.add(allSong.get(c));
                    break;
                case 1:
                    break;
            }
        }
        return targetSong;
    }

    /**
     * テーブル内のStatusを全て0(未再生)に書き換える
     */
    private void resetStatus() {
        for (int i = 0; i < allSong.size(); i++) {
            allSong.get(i).setStatus(0);
        }
    }

    public List<Song> findAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }
}
