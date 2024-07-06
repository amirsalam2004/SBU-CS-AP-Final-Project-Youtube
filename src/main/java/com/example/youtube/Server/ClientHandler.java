package com.example.youtube.Server;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
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
        try {
            File videoFile=new File(videoID+".mp4");
            FileInputStream fileInputStream = new FileInputStream(videoFile);
            byte[] buffer = new byte[4 * 1024];
            int bytes;
            out.writeLong(videoFile.length());
            while ((bytes = fileInputStream.read(buffer))
                    != -1) {
                // Send the file to Server Socket
                out.write(buffer, 0, bytes);
                out.flush();
            }
        }catch (FileNotFoundException e){
            out.write(0);
            out.flush();
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            out.write(0);
            out.flush();
            System.out.println(e.getMessage());
        }
    }
    public void sendImageBytes(String imagePath) throws IOException {
        try {
            byte[] buffer = Files.readAllBytes(Paths.get(imagePath));
            out.write(buffer);
            out.flush();
        }catch (FileNotFoundException e){
            out.write(0);
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            out.write(0);
            System.out.println(e.getMessage());
        }
    }
}
