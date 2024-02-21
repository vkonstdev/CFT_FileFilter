package com.vkonstdev;

import java.util.ArrayList;
import java.util.List;

public class CommandLineParser {
    public static CommandLineArgs parse(String[] args) {
        CommandLineArgs cla = new CommandLineArgs();
        List<String> tempList = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if ("-o".equals(args[i])) {
                cla.setOutputPath(args[i + 1]);
                i++;
            } else if ("-p".equals(args[i])) {
                cla.setFilePrefix(true);
                cla.setFilePrefix(args[i + 1]);
                i++;
            } else if ("-a".equals(args[i])) {
                cla.setAppendMode(true);
            } else if ("-s".equals(args[i])) {
                cla.setShortStatistics(true);
            } else if ("-f".equals(args[i])) {
                cla.setShortStatistics(false);
            } else if (args[i].contains(".txt")) {
                tempList.add(args[i]);
            }
        }
        cla.setInputFiles(tempList);
        return cla;
    }
}
