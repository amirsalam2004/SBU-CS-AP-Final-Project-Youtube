package com.example.youtube.Server;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Client {
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
            return input;
        }catch (IOException e){
            System.out.println(e.getMessage());
            return "0";
        }
    }
    public void closeConnection(){
        try {
            // Close input and output streams and the client socket when done
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
    public boolean sendImageBytes(String imagePath) throws IOException {
        Path path= Paths.get(imagePath);
        if (!Files.exists(path)){
            // if file doesn't exist
            return false;
        }
        try {
            byte[] buffer = Files.readAllBytes(path);
            out.write(buffer);
            out.flush();
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
        FileOutputStream fos = new FileOutputStream(imageID + ".jpeg");
        try {
            int nRead;
            ByteArrayOutputStream buffer=new ByteArrayOutputStream();
            byte[] data=new byte[1024];
            while((nRead = in.read(data,0,data.length)) != -1){
                buffer.write(data,0,nRead);
            }
            buffer.flush();
            byte[] image=buffer.toByteArray();
            fos.write(image);
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
}
