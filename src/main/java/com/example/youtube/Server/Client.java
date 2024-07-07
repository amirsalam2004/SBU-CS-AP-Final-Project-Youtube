package com.example.youtube.Server;

import com.example.youtube.Model.Channel;
import com.example.youtube.Model.Comment;
import com.example.youtube.Model.User;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.google.gson.Gson;

public class Client {
    private static final Gson gson = new Gson();
    private static final int SERVER_PORT = 3000;
    private final String SERVER_IP;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    public Client(String IP) throws IOException{
        SERVER_IP=IP;
        try {
            this.socket = new Socket(SERVER_IP, SERVER_PORT);
            // Create DataOutputStream for sending requests to the server
            this.out = new DataOutputStream(socket.getOutputStream());
            // Create DataInputStream for reading responses from the server
            this.in= new DataInputStream(socket.getInputStream());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public boolean sendRequest(int type,int endpoint,String body) throws  IOException{
        try {
            String request=type+"#"+endpoint+"#"+body;
            System.out.println("request is "+request);
            out.writeUTF(request);
            return true;
        }catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean sendRequest(int type,String body) throws  IOException{
        try {
            String request=type+"#"+body;
            System.out.println("[REQUEST] = "+request);
            out.writeUTF(request);
            return true;
        }catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public String getResponse() throws IOException{
        try {
            String input=in.readUTF();
            System.out.println("Response is "+ input);
            return input;
        }catch (IOException e){
            System.out.println(e.getMessage());
            return "0";
        }
    }
    public void closeConnection(){
        try {
            // Close input and output streams and the client socket when done
            System.out.println("in this is close connection in line 48 client ");
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean sendVideoBytes(String videoID) throws IOException {
        File videoFile=new File(videoID+".mp4");
        if (!videoFile.exists()) {
            return false;
        }
        try(FileInputStream fileInputStream = new FileInputStream(videoFile);) {
            byte[] buffer = new byte[4 * 1024];
            int bytes;
            out.writeLong(videoFile.length());
            while ((bytes = fileInputStream.read(buffer))
                    != -1) {
                // Send the file to Server Socket
                out.write(buffer, 0, bytes);
                out.flush();
            }
            fileInputStream.close();
            return true;
        }catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean sendImageBytes(String imageID) throws IOException {
        File imageFile=new File(imageID+".jpg");
        if (!imageFile.exists()) {
            return false;
        }
        try(FileInputStream fileInputStream = new FileInputStream(imageFile)) {
            byte[] buffer = new byte[4 * 1024];
            int bytes;
            out.writeLong(imageFile.length());
            while ((bytes = fileInputStream.read(buffer)) != -1) {
                // Send the file to Server Socket
                out.write(buffer, 0, bytes);
                out.flush();
            }
            fileInputStream.close();
            return true;
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean getVideoBytes(String videoID) throws IOException{
        FileOutputStream fos = new FileOutputStream(videoID + ".mp4");
        try {
            long fileSize = in.readLong();
            byte[] buffer = new byte[4*1024];
            long totalBytesRead = 0;
            int bytesRead;
            while (totalBytesRead < fileSize && (bytesRead = in.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
            }
            fos.flush();
            return true;
        }catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }finally {
            fos.close();
        }
    }
    public boolean getImageBytes(String imageID) throws IOException{
        FileOutputStream fos = new FileOutputStream(imageID+ ".jpg");
        try {
            long fileSize = in.readLong();
            byte[] buffer = new byte[4*1024];
            long totalBytesRead = 0;
            int bytesRead;
            while (totalBytesRead < fileSize && (bytesRead = in.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
            }
            fos.flush();
            return true;
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }finally {
            fos.close();
        }
    }
    // this make a request to create a new user and return server response
    public String addUserRequest(User user) throws IOException{
        try {
            String request="2#21#"+gson.toJson(user);
            out.writeUTF(request);
            String response=in.readUTF();
            return response;
        }catch (IOException e){
            return "0";
        }
    }
    // this make a request to create a new channel and return server response
    public String addChannelRequest(Channel channel) throws IOException{
        try {
            String request="2#22#"+gson.toJson(channel);
            out.writeUTF(request);
            String response=in.readUTF();
            return response;
        }catch (IOException e){
            return "0";
        }
    }
    // this make a request to create a new comment and return server response
    public String addCommentRequest(Comment comment) throws IOException{
        try {
            String request="2#23#"+gson.toJson(comment);
            out.writeUTF(request);
            String response=in.readUTF();
            return response;
        }catch (IOException e){
            return "0";
        }
    }
    public String getUserRequest(String email, String passWord) throws IOException {
        try {
            String request = "1#11#"+email+"#"+passWord;
            out.writeUTF(request);
            String response = in.readUTF();
            return response;
        } catch (IOException e) {
            return "0";
        }
    }
    public String getChannelrRequest(String identifier, int number) throws IOException {
        try {
            String request = "1#11#"+identifier+"#"+number;
            out.writeUTF(request);
            String response = in.readUTF();
            return response;
        } catch (IOException e) {
            return "0";
        }
    }
}
