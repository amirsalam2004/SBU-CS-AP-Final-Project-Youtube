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

    int i = 0;


    @Override
    public void run() {
        try {
            while (true) {

                String[] request = in.readUTF().split("#", 2);
                String response = "";
                switch (request[0]) {
                    case "1":
                        System.out.println("GET METHOD  -->"+request[1]);
                        response = getApiService.handleRequest(request[1]);
                        break;
                    case "2":
                        System.out.println("ADD METHOD  -->"+request[1]);
                        response = addApiService.handleRequest(request[1]);
                        break;
                    case "3":
                        System.out.println("DELETE  METHOD  -->"+request[1]);
                        response = deleteApiService.handleRequest(request[1]);
                        break;
                    case "4":
                        System.out.println("UPDATE  METHOD  -->"+request[1]);
                        response = updateApiService.handleRequest(request[1]);
                        break;
                    default:
                        response = "0";
                        break;
                }
                send(response);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                in.close();
                out.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String response) throws IOException {
        out.writeUTF(response);
    }

}