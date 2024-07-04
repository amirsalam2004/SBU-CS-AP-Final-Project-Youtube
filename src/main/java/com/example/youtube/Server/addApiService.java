package com.example.youtube.Server;

import com.example.youtube.Model.*;
import com.example.youtube.DataBase.*;

import com.google.gson.Gson;
public class addApiService {
    private static final Gson gson = new Gson();

    public static String handleRequest(String request) {
        String[] parts = request.split("#", 2);
        String endpoint = parts[0];
        String body = parts.length > 1 ? parts[1] : "";

        switch (endpoint) {
            case "21":
                return addUser(body);
            case "22":
                return addChannel(body); // image?
            case "23":
                return addComment(body);
            case "24":
                return addPlayList(body); // image?
            case "25":
                return addVideo(body);  // video?
            case "26":
                return addVideoToHistory(body);
            case "27":
                return addFollowerOrFollowing(body);
            case "28":
                return addKarma(body);
            default:
                return gson.toJson(new addApiService.ErrorResponse("Unknown endpoint"));
        }
    }
    //to add a new user
    private static String addUser(String userJson) {
        try {
            User user = gson.fromJson(userJson, User.class);
            if(DataBaseManager.Cr_User(user)) {
                //If the changes are applied successfully, return 1
                return "1";
            }
            return "0";
        }catch (Exception e){
            //if wasn't successfully, return 0
            return "0";
        }
    }
    //to add a new channel
    private static String addChannel(String channelJson) {
        try {
            Channel channel = gson.fromJson(channelJson, Channel.class);
            if(DataBaseManager.Cr_Chanel(channel)) {
                //If the changes are applied successfully, return 1
                return "1";
            }
            return "0";
        }catch (Exception e){
            //if wasn't successfully, return 0
            return "0";
        }
        //                            ###
        //                            prifile and image
        //                            ###
    }
    //to add a new comment
    private static String addComment(String commentJson) {
        try {
            Comment comment = gson.fromJson(commentJson, Comment.class);
            if(DataBaseManager.Cr_comment(comment)) {
                //If the changes are applied successfully, return 1
                return "1";
            }
            return "0";
        }catch (Exception e){
            //if wasn't successfully, return 0
            return "0";
        }
    }
    //to add a new playlist
    private static String addPlayList(String playlistJson) {
        try {
            PlayList playList= gson.fromJson(playlistJson, PlayList.class);
            if(DataBaseManager.Cr_PlayList(playList)) {
                //If the changes are applied successfully, return 1
                return "1";
            }
            return "0";
        }catch (Exception e){
            //if wasn't successfully, return 0
            return "0";
        }
        //                             ###
        //                             image?
        //                             ###
    }
    //to add a new comment
    private static String addVideo(String videoJson) {
        try {
            Video video= gson.fromJson(videoJson, Video.class);
            if(DataBaseManager.Cr_Video(video)) {
                //If the changes are applied successfully, return 1
                return "1";
            }
            return "0";
        }catch (Exception e){
            //if wasn't successfully, return 0
            return "0";
        }
        //                             ###
        //                             video?
        //
    }
    // to add a video to a user history
    private static String addVideoToHistory(String videoInfo) {
        try {
            String[] info=videoInfo.split("#",2);
            if(DataBaseManager.ADD_video_history(info[0],info[1])) {
                //If the changes are applied successfully, return 1
                return "1";
            }
            return "0";
        }catch (Exception e){
            //if wasn't successfully, return 0
            return "0";
        }
    }
    // to add a follower or following
    private static String addFollowerOrFollowing(String followInfo) {
        try {
            String[] info=followInfo.split("#",3);
            if(DataBaseManager.ADD_follower_following(info[0],info[1],Integer.parseInt(info[2]))) {
                //If the changes are applied successfully, return 1
                return "1";
            }
            return "0";
        }catch (Exception e){
            //if wasn't successfully, return 0
            return "0";
        }
    }
    private static String addKarma(String karmaInfo) {
        try {
            String[] info=karmaInfo.split("#",3);
            if(DataBaseManager.Karma(Integer.parseInt(info[0]),info[1],info[2])) {
                //If the changes are applied successfully, return 1
                return "1";
            }
            return "0";
        }catch (Exception e){
            //if wasn't successfully, return 0
            return "0";
        }
    }
    private static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
