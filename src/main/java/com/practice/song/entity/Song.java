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
    private String  tag;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getTag() {
        return tag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @PostUpdate
    public void onPreUpdate() {
        setStatus(status);
        setTag(tag);
    }
}
