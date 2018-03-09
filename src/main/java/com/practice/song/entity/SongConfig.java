package com.practice.song.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SongConfig {

    @Id
    private int id;
    private String name;
    private int status;

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public int getStatus(){
        return status;
    }

    public void setName(){
        this.name = name;
    }
}
