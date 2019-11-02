package com.company;

import org.apache.commons.cli.*;

public class Main {


    public static void main(String[] args) throws ParseException, InterruptedException {
        Options options = new Options();

        Option threadsNum = Option.builder("t")
                .hasArg(true)
                .longOpt("Number_of_Threads")
                .numberOfArgs(1)
                .build();
        Option port = Option.builder("a")
                .hasArg(true)
                .longOpt("number")
                .numberOfArgs(Option.UNLIMITED_VALUES)
                .valueSeparator(',')
                .build();
        Option ip = Option.builder("i")
                .hasArg(true)
                .longOpt("IP_address")
                .numberOfArgs(Option.UNLIMITED_VALUES)
                .valueSeparator(',')
                .build();
        options.addOption(port);
        options.addOption(ip);
        options.addOption(threadsNum);
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        ArgParser test = new ArgParser(cmd);
        test.parseIp();
    }
}
