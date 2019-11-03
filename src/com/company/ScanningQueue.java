package com.company;

class ScanningQueue {
    private int threadsMax;
    int activeThreads =0;
    ScanningQueue(int threadsMax){
        this.threadsMax = threadsMax;
    }
    void addTask(String ip, int port) throws InterruptedException {
        while(activeThreads>=threadsMax){
            Thread.sleep(100);
        }
        activeThreads++;
        new ScanThread(ip, port, this);
    }
    synchronized void freeThread(){
        activeThreads--;
    }
}
