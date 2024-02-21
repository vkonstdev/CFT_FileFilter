package com.vkonstdev;

public class Main {

    public static void main(String[] args) {
        CommandLineArgs cla = CommandLineParser.parse(args);
        FilesFilter filesFilter = new FilesFilter(cla);
        filesFilter.filter();
    }
}