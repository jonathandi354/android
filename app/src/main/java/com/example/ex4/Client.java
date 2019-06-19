package com.example.ex4;



import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private String ip;
    private int port;
    private OutputStream stream;
    private Socket socket;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void Connect() {
        try {
            InetAddress server =InetAddress.getByName(ip);
            socket = new Socket(server, port);
            try {
                stream =  socket.getOutputStream();

            } catch (Exception e) {

            }
        } catch (Exception e) {

        }
    }

    public void send(String msg) {
        try {
            stream.write(msg.getBytes());
            stream.flush();

        } catch (Exception e) {

        }
    }
    public void Disconnect() {
        try {
            stream.close();
            socket.close();

        } catch (Exception e) {

        }
    }
}
