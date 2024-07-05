package com.example.youtube.Model;

import org.w3c.dom.ls.LSException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Video {
    private String ID;
    private String uploadTime;
    private Integer view;
    private Integer duration; //??
    private Integer like;
    private Integer deslike;
    private String name;
    private String description;
    private String category;
    private String path;

    private String IDChanel;
    public Video(String ID,String idchal,String name,String description,String uplaodTime,
                  Integer duration,Integer like,Integer deslike,Integer view) {
        this.name = name;
        this.description = description;
        this.view = view;
        this.like = like;
        this.deslike = deslike;
        this.uploadTime = uplaodTime;
        this.duration=duration;
        this.ID=ID;
        this.IDChanel=idchal;
        this.setPath(ID);


    }

    public String getID() {
        return ID;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDeslike() {
        return deslike;
    }

    public String getName() {
        return name;
    }

    public Integer getLike() {
        return like;
    }

    public int  getView() {
        return view;
    }

    public int  getDuration() {
        return duration;
    }

    public String getUploadTime() {
        return uploadTime;
    }


    public void editName(String name) {
        this.name = name;
    }

    public void editDescription(String description) {
        this.description = description;
    }

    public void Deslike(Integer change) {
        this.deslike += change;
    }

    public void Like(Integer change) {
        this.like += change;
    }

    public void appendView() {
        this.view ++;
    }



    public String getIDChanel() {
        return IDChanel;
    }

    public void setIDChanel(String IDChanel) {
        this.IDChanel = IDChanel;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category= category;
    }
}
