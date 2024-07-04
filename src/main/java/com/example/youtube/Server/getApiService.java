package com.example.youtube.Server;

import com.example.youtube.Model.*;
import com.example.youtube.DataBase.*;

import com.google.gson.Gson;

import java.util.ArrayList;

public class getApiService {
    private static final Gson gson = new Gson();
//    private static final DataBaseManager dataManager = new DataBaseManager();

    public static String handleRequest(String request) {
        String[] parts = request.split("#", 2);
        String endpoint = parts[0];
        String body = parts.length > 1 ? parts[1] : "";

        switch (endpoint) {
            case "11":
                return getUser(body);
            case "12":
                return getChannel(body);
            case "13":
                return getVideoComments(body);
            case "14":
                return getChannelComments(body);
            case "15":
                return getUserComments(body);
            case "16":
                return getChannelPlaylists(body);
            default:
                return gson.toJson(new ErrorResponse("Unknown endpoint"));
        }
    }

    private static String getUser(String userInfo) {
        try {
            String[] info=userInfo.split("#",2);
            User user = DataBaseManager.get_User(info[0],info[1]);
            String response="1+"+gson.toJson(user);
            return response;
        }catch (Exception e){
            return ("0");
        }
    }
    //get Channel with  username or ID_chanel
    private static String getChannel(String channelInfo) {
        try {
            String[] info=channelInfo.split("#",2);
            Channel channel=DataBaseManager.get_Channel(info[0],Integer.parseInt(info[1]));
            String response="1+"+gson.toJson(channel);
            return response;
        }catch (Exception E){
            return ("0");
        }
    }
    // this is for get the comments of a video
    private static String getVideoComments(String videoInfo) {
        try {
            ArrayList<Comment> comments=DataBaseManager.getListComment(videoInfo);
            String response="1+"+gson.toJson(comments);
            return response;
        }catch (Exception e){
            return ("0");
        }
    }
    //comment that User get for his videos
    private static String getChannelComments(String channelInfo) {
        try {
            ArrayList<Comment> comments=DataBaseManager.getListComment_userGet(channelInfo);
            String response="1+"+gson.toJson(comments);
            return response;
        }catch (Exception e){
            return ("0");
        }
    }
    //get list of comment that the User send
    private static String getUserComments(String userInfo) {
        try {
            ArrayList<Comment> comments=DataBaseManager.getListComment_userGet(userInfo);
            String response="1+"+gson.toJson(comments);
            return response;
        }catch (Exception e){
            return ("0");
        }
    }
    //get list of playlist in chanel
    private static String getChannelPlaylists(String channelInfo) {
        try {
            ArrayList<PlayList> playLists=DataBaseManager.getPlayList(channelInfo);
            String response="1+"+gson.toJson(playLists);
            return response;
        }catch (Exception e){
            return ("0");
        }
    }
    private static class RequestData {
        private Long userId;
        private Long videoId;

        public Long getUserId() {
            return userId;
        }

        public Long getVideoId() {
            return videoId;
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
