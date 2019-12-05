package com.company;

public class SocketContainer {
    private int port;
    private String ip;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    SocketContainer(int port, String ip) {
        this.port = port;
        this.ip = ip;

    }
}
