package com.practice.song.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "music")
public class Song implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private int favorite;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    @PostUpdate
    public void onPreUpdate() {
        setStatus(status);
        setFavorite(favorite);
    }
}
