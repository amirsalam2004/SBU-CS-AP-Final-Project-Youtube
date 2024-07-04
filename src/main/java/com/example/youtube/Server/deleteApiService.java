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
