package com.example.youtube.Server;

import java.io.*;
import java.net.Socket;

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
            System.out.println("request is "+request);
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
}
