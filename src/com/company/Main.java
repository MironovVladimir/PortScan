package com.company;

import org.apache.commons.cli.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static Logger log = Logger.getLogger(Main.class.getName());
    public static void main(String[] args){
        Options options = new Options();

        Option threadsNum = Option.builder("t")
                .hasArg(true)
                .argName("number")
                .longOpt("Number_of_Threads")
                .numberOfArgs(1)
                .build();
        Option port = Option.builder("p")
                .hasArg(true)
                .argName("port,port,port-port")
                .longOpt("port")
                .numberOfArgs(Option.UNLIMITED_VALUES)
                .valueSeparator(',')
                .build();
        Option ip = Option.builder("h")
                .hasArg(true)
                .longOpt("IP_address")
                .argName("host,host,host-host")
                .numberOfArgs(Option.UNLIMITED_VALUES)
                .valueSeparator(',')
                .build();
        options.addOption(port);
        options.addOption(ip);
        options.addOption(threadsNum);
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            ArgParser test = new ArgParser(cmd);
            test.parseIp();
        }catch(ParseException | NumberFormatException e){
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp("PortScan", options);
            log.log(Level.WARNING,"error");
        }catch(InterruptedException e){
            System.out.println("something gone wrong, please red logs");
            log.log(Level.WARNING,"error");
        }
    }
}
// logger java