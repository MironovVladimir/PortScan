package com.company;

import org.apache.commons.cli.CommandLine;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Level;

class ArgParser {
    private CommandLine cmd;
    private ExecutorService service;
    List<SocketContainer> socketList = new LinkedList<>();
    ArgParser(CommandLine _cmd){
        this.cmd = _cmd;
        int threads = Integer.parseInt(cmd.getOptionValue("t"));
        if(threads>0) service = Executors.newFixedThreadPool(Integer.parseInt(cmd.getOptionValue("t")));

        else System.out.println("incorrect threads number");
    }

    void parseIp() throws NumberFormatException {
        for(String str : cmd.getOptionValues('h')){
            boolean isValid = true;
            Main.log.info("recived '"+ str + "' ip argument ");
            String[] blocks = str.split("\\.");
            for(int i =0; i<=2; i++) {
                if (Integer.parseInt(blocks[i]) > 225 || Integer.parseInt(blocks[i]) < 0)
                    isValid = false;
            }
            if(blocks.length==4&&isValid) ipArgProcessor(blocks);
            else  {
                System.out.println("incorrect ip address "+str);
                Main.log.log(Level.WARNING, "incorrect ip address "+str, new Throwable());
            }
        }
        Collections.shuffle(socketList);
        for(SocketContainer i : socketList){
            service.execute(new ScanThread(i));
        };
        service.shutdown();
    }

    private void parsePort(String addr) {
        for(String str : cmd.getOptionValues('p')){
            Main.log.info("recived '"+str+"' port argument");
            String[] vals = str.split("-");
            if(vals.length ==2){
                for(int i = Integer.parseInt(vals[0]); i<=Integer.parseInt(vals[1]);i++) socketList.add(new SocketContainer(i, addr));
            }
            else if(vals.length == 1) socketList.add(new SocketContainer(Integer.parseInt(vals[0]),addr ));
        }
    }

    private void dashProcessor(String[] subBlocks, String[] blocks) {
        int from = Integer.parseInt(subBlocks[0]);
        int to = Integer.parseInt(subBlocks[1]);
        for(int i = from; i<= to;i++){
            if(from==to||to>225){
                System.out.println("incorrect '-' using in " +blocks[3]);
                Main.log.log(Level.WARNING, "incorrect '-' using in " +blocks[3], new Throwable());
                break;
            }
            String addr = blocks[0]+"."+blocks[1]+"."+blocks[2]+"."+i;
            parsePort(addr);
        }
    }

    private void ipArgProcessor(String[] blocks) {
        String[] subBlocks = blocks[3].split("-");
        if(subBlocks.length>2) {
            System.out.println("incorrect '-' using in "+blocks[3]);
            Main.log.log(Level.WARNING, "incorrect '-' using in "+blocks[3], new Throwable());
        }
        else if(subBlocks.length==2) dashProcessor(subBlocks, blocks);
        else parsePort(Integer.parseInt(blocks[0])+
                    "."+Integer.parseInt(blocks[1])+
                    "."+Integer.parseInt(blocks[2])+
                    "."+subBlocks[0]);
    }
}
