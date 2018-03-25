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

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @PostUpdate
    public void onPreUpdate() {
        setStatus(status);
    }
}
