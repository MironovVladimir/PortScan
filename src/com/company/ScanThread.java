package com.company;

import java.io.IOException;
import java.net.Socket;

public class ScanThread implements Runnable {

    private String ip;
    private int port;

    public ScanThread(SocketContainer input){
        this.ip = input.getIp();
        this.port = input.getPort();
    }

    @Override
    public void run() {
        try {
            Socket s = new Socket(ip,port);
        } catch (IOException e) {
            Main.log.info(port +" port on "+ip+" ip has not answered");
            return;
        }
        System.out.println("found opened port: "+port+" on IP: "+ip);
        Main.log.info(port +" port on "+ip+" ip has answered");
    }
}
