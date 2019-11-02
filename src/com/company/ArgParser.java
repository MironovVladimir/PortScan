package com.company;

import org.apache.commons.cli.CommandLine;

class ArgParser {
    private CommandLine cmd;
    private ScanningQueue queue;
    ArgParser(CommandLine cmd){
        this.cmd = cmd;
        int threads = Integer.parseInt(cmd.getOptionValue("t"));
        if(threads>0) queue = new ScanningQueue(threads);
        else System.out.println("incorrect threads number");
    }

    void parseIp() throws InterruptedException {
        for(String str : cmd.getOptionValues('i')){
            boolean isValid = true;
            String[] blocks = str.split("\\.");
            for(int i =0; i<=2; i++) if(Integer.parseInt(blocks[i])>225||Integer.parseInt(blocks[i])<0) isValid = false;
            if(blocks.length==4&&isValid) {
                String[] subBlocks = blocks[3].split("-");
                if(subBlocks.length>2) System.out.println("incorrect '-' using in "+blocks[3]);
                else if(subBlocks.length==2){
                    int from = Integer.parseInt(subBlocks[0]);
                    int to = Integer.parseInt(subBlocks[1]);
                    for(int i = from; i<= to;i++){
                        if(from==to||to>225){
                            System.out.println("incorrect '-' using in " +blocks[3]);
                            break;
                        }
                        String addr = blocks[0]+"."+blocks[1]+"."+blocks[2]+"."+i;
                        parsePort(addr);
                    }
                }
                else parsePort(Integer.parseInt(blocks[0])+"."+Integer.parseInt(blocks[1])+"."+Integer.parseInt(blocks[2])+"."+subBlocks[0]);
            }
            else  System.out.println("incorrect ip address "+str);
        }
    }

    private void parsePort(String addr) throws InterruptedException {
        for(String str : cmd.getOptionValues('a')){
            String[] vals = str.split("-");
            if(vals.length ==2){
                for(int i = Integer.parseInt(vals[0]); i<=Integer.parseInt(vals[1]);i++) queue.addTask(addr,i);
            }
            else if(vals.length == 1) queue.addTask(addr,Integer.parseInt(vals[0]));
        }
    }
}
