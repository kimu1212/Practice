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
    private int count = -1;
//    private final int PEEKMAX = 5; // 何曲後まで格納するか
//    private List<Song> allSong = findAll();
//    private List<Song> songBox = StoreSong(PEEKMAX); // シャッフル楽曲格納用
//
//
//    /**
//     * ランダム5曲のリストを返す
//     *
//     * @return songBox
//     */
//    public List<Song> peekQueue() {
//        return songBox;
//    }
//
//    /**
//     * 音楽をシャッフルして格納
//     *
//     * @param end 格納の回数(未再生の楽曲の数 Max:5)
//     * @return songBox
//     */
//    private List<Song> StoreSong(int end) {
//        int count = 0;
//        List<Song> songBox = new ArrayList<>(); // シャッフル楽曲格納用
//        Random rand = new Random();
//        while (count < end) {
//            int i = rand.nextInt(allSong.size());
//            if (allSong.get(i).getStatus() == 0) {
//                songBox.add(allSong.get(i));
//                allSong.get(i).setStatus(1);
//                count++;
//            }
//        }
//        return songBox;
//    }
//
//
//    /**
//     * 次の曲を決定し、後の５曲(songBox)を更新
//     */
//    public Song getNextSong() {
//        Random rand = new Random();
//        Song nextSong = songBox.get(0);
//        int r = rand.nextInt(allSong.size());
//        songBox.remove(0);
//        if(countStatus() == 0){ // 全部再生し終えたらstatusをリセット
//            resetStatus();
//        }
//        while (true) {
//            if (allSong.get(r).getStatus() == 0) {
//                songBox.add(allSong.get(r));
//                allSong.get(r).setStatus(1);
//                break;
//            }
//        }
//        return nextSong;
//    }
//
//
//    private int countStatus() {
//        int count = 0;
//        for(int i = 0; i < allSong.size(); i++){
//            int status = allSong.get(i).getStatus();
//            switch (status){
//                case 0:
//                    count++;
//                    break;
//
//                case 1:
//                    break;
//            }
//        }
//        return count;
//    }
//
//
//    /**
//     * テーブル内のStatusを全て0(未再生)に書き換える
//     */
//    private void resetStatus() {
//        for (int i = 0; i < allSong.size(); i++) {
//            allSong.get(i).setStatus(0);
//        }
//    }
    public int returnCount() {
        count++;
        return count;
    }
    public List<Song> findAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }
}
