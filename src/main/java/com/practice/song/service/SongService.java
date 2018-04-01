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
    private final int PEEKMAX = 5; /* 何曲後まで格納するか */
    private int playNum = PEEKMAX + 1;
    /**
     * テーブルの中身をid順列で取得
     * @return id順でソートしたテーブルのリストデータ
     */
    public List<Song> findAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }

    /**
     * Entityに登録したデータを反映
     */
    private void updateData() {
        repository.flush();
    }

    /**
     * PEEKMAX個の曲をシャッフルして格納
     *
     * @param allSong データベース内の楽曲
     * @return songBox 次の5曲
     */
    public List<Song> peekQueue(List<Song> allSong) {
        int count = 0;
        List<Song> songBox = new ArrayList<>(); /* シャッフル楽曲格納用 */
        Random rand = new Random();
        while (count < PEEKMAX + 1) { // PEEKMAX分だけsongBoxに曲を格納
            int i = rand.nextInt(allSong.size());
            if (allSong.get(i).getStatus() == 0) {
                if (songBox.size() > 0) {
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
            updateData();
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
    public List<Song> getNextSong(List<Song> allSong, List<Song> songBox) {
        Random rand = new Random();
        if (countStatus(allSong) == 0) { // 全部再生し終えたらstatusをリセット
            resetStatus(allSong);
        }
        while (true) {
            int r = rand.nextInt(allSong.size());

            // 未再生かつ前後で楽曲が被らないように
            if (allSong.get(r).getStatus() == 0 && allSong.get(r).getId() != songBox.get(songBox.size() - 1).getId()) {
                songBox.add(allSong.get(r));
                allSong.get(r).setStatus(1);
                songBox.remove(0);
                updateData();
                break;
            }
        }
        return songBox;
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
     * @param allSong データベース内の楽曲
     */
    public void resetStatus(List<Song> allSong) {
        for (int i = 0; i < allSong.size(); i++) {
            allSong.get(i).setStatus(0);
        }
        updateData();
    }

    public List<Song> getSortSongs(List<Song> allSong) {
        List<Song> sortBox = new ArrayList<>();
        for (int i = 0; i < PEEKMAX + 1; i++) {
            sortBox.add(allSong.get(i));
        }
        playNum = PEEKMAX + 1;
        return sortBox;
    }

    public List<Song> getNextSortSong(List<Song> allSong, List<Song> songBox) {
        if(allSong.size() > playNum){
            songBox.add(allSong.get(playNum));
            playNum++;
        } else {
            songBox.add(allSong.get(0));
            playNum = 1;
        }
        songBox.remove(0);
        return songBox;
    }
}
