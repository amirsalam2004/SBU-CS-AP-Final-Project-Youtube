package com.example.youtube.Server;

import com.example.youtube.Model.*;
import com.example.youtube.DataBase.*;

import com.google.gson.Gson;

public class deleteApiService {
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
    //to delete a video
    private static String deleteUser(String videoInfo) {
        try {
            String[] info=videoInfo.split("#",2);
            DataBaseManager.DE_Video(info[0],info[1]);
            //If the changes are applied successfully, return 1
            return "1";
        }catch (Exception e){
            //if wasn't successfully, return 0
            return "0";
        }
    }
    //to delete playlist
    private static String deletePalylist(String playlistInfo) {
        try {
            DataBaseManager.deletePlayList(playlistInfo);
            //If the changes are applied successfully, return 1
            return "1";
        }catch (Exception e){
            //if wasn't successfully, return 0
            return "0";
        }
    }

    // ?????????????????????????????/
    private static String deleteComment(String commentInfo) {
        try {
            String[] info=commentInfo.split("#",2);
            DataBaseManager.delete_Comment_writer(info[0],info[1]);
            //If the changes are applied successfully, return 1
            return "1";
        }catch (Exception e){
            //if wasn't successfully, return 0
            return "0";
        }
    }
    private static String unfollow(String followInfo) {
        try {
            String[] info=followInfo.split("#",3);
            DataBaseManager.UnFollow(info[0],info[1],Integer.parseInt(info[2]));
            //If the changes are applied successfully, return 1
            return "1";
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
