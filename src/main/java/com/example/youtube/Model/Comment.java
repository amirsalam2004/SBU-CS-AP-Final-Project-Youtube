package com.example.youtube.Model;

import java.time.LocalDate;

public class Comment {
    private String text;
    private String userID;
    private String userUsername;
    private String videoID;
    private Integer like;
    private LocalDate time;
    private Integer deslike;
    private String IDComment;
    public Comment(String text,String userID,String userUsername,String videoID,
                   String time,Integer like,Integer deslike,String id){
        this.IDComment=id;
        this.deslike=deslike;
        this.like=like;
        this.text=text;
        this.userID=userID;
        this.videoID=videoID;
        this.userUsername=userUsername;
        this.time=LocalDate.parse(time);
    }

    public Integer getDeslike() {
        return deslike;
    }

    public Integer getLike() {
        return like;
    }

    public LocalDate getTime() {
        return time;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public String getVideoID() {
        return videoID;
    }

    public String getText() {
        return text;
    }

    public void Deslike(Integer change) {
        this.deslike+=change;
    }

    public void Like(Integer change) {
        this.like+=change;
    }

    public void editText(String text) {
        this.text = text;
    }
}
