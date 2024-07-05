package com.example.youtube.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
                System.out.println(request[1]+"123123");
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
}
