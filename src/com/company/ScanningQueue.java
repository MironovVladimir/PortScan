package com.company;

class ScanningQueue {
    private static final int SLEEP_TIMEOUT_MS = 100;
    private int threadsMax;
    int activeThreads = 0;
    ScanningQueue(int _threadsMax){
        this.threadsMax = _threadsMax;
    }
    void addTask(String ip, int port) throws InterruptedException {
        while(activeThreads>=threadsMax){
            Thread.sleep(SLEEP_TIMEOUT_MS);
            Main.log.info(activeThreads+" from");
        }
        activeThreads++;
        new ScanThread(ip, port, this);
        Main.log.info("checking " + port +" port on "+ip+" ip");
    }
    synchronized void freeThread(){
        activeThreads--;
    }
}
