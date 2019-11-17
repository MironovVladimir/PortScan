package com.company;

import java.io.IOException;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class ScanThread implements Runnable {

    private String ip;
    private int port;

    public ScanThread(String ip, int port) {
        this.ip = ip;
        this.port = port;
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
