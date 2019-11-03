package com.company;

import java.io.IOException;
import java.net.Socket;

public class ScanThread implements Runnable {
    private String ip;
    private int port;
    private ScanningQueue manager;
    ScanThread(String ip, int port, ScanningQueue manager){
        this.ip = ip;
        this.manager = manager;
        this.port = port;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Socket s = new Socket(ip,port);
        } catch (IOException e) {
            manager.freeThread();
            return;
        }
        System.out.println("found opened port: "+port+" on IP: "+ip);
        manager.freeThread();
    }
}
