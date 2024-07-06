package com.example.youtube.Server;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientHandler implements Runnable {
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        this.in = new DataInputStream(client.getInputStream());
        this.out = new DataOutputStream(client.getOutputStream());
    }

    @Override
    public void run() {
        while (true){
            try {
                String[] request=in.readUTF().split("#",2);
                String response="";
                switch (request[0]){
                    case "1":
                        response=getApiService.handleRequest(request[1]);
                        break;
                    case "2":
                        response=addApiService.handleRequest(request[1]);
                        break;
                    case "3":
                        response=deleteApiService.handleRequest(request[1]);
                        break;
                    case "4":
                        response=updateApiService.handleRequest(request[1]);
                        break;
                    case "5":
                        getVideoBytes(request[1]);
                        break;
                    case "6":
                        getImageBytes(request[1]);
                        break;
                    case "7":
                        sendVideoBytes(request[1]);
                        break;
                    case "8":
                        sendImageBytes(request[1]);
                    default:
                        response="0";
                        break;
                }
                send(response);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }finally {
                try {
                    // Close input and output streams and the client socket when done
                    in.close();
                    out.close();
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void send(String response) throws IOException{
        out.writeUTF(response);
    }
    public void sendVideoBytes(String videoID) throws IOException {
        File videoFile=new File(videoID+".mp4");
        if (!videoFile.exists()) {
            out.writeInt(0);
            out.flush();
            return;
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
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
    public void sendImageBytes(String imagePath) throws IOException {
        Path path=Paths.get(imagePath);
        if (!Files.exists(path)){
            // if file doesn't exist
            out.writeInt(0);
            out.flush();
            return;
        }
        try {
            byte[] buffer = Files.readAllBytes(path);
            out.write(buffer);
            out.flush();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void getVideoBytes(String videoID) throws IOException{
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
        }catch (IOException e){
            System.out.println(e.getMessage());
        }finally {
            fos.close();
        }
    }
    public void getImageBytes(String imageID) throws IOException{
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
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }finally {
            fos.close();
        }
    }
}
