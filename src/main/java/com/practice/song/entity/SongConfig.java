package com.practice.song.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "music")
public class SongConfig {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
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

    public void setName() {
        this.name = name;
    }

    public void setId() {
        this.id = id;
    }

    public void setStatus() {
        this.status = status;
    }
}
