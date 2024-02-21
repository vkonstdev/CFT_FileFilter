package com.vkonstdev;

import java.util.ArrayList;
import java.util.List;

public class CommandLineArgs {
    private List<String> inputFiles;
    private String outputPath;
    private String filePrefix;
    private boolean isShortStatistics;
    private boolean isFilePrefix;
    private boolean isAppendMode;

    public CommandLineArgs() {
        inputFiles = new ArrayList<>();
        outputPath = ".";
        filePrefix = "";
        isShortStatistics = false;
        isFilePrefix = false;
        isAppendMode = false;
    }

    public String getOutputPath() {
        return outputPath;
    }
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
    public String getFilePrefix() {
        return filePrefix;
    }
    public void setFilePrefix(String filePrefix) {
        this.filePrefix = filePrefix;
    }
    public boolean isFilePrefix() {
        return isFilePrefix;
    }
    public void setFilePrefix(boolean filePrefix) {
        isFilePrefix = filePrefix;
    }
    public boolean isShortStatistics() {
        return isShortStatistics;
    }
    public void setShortStatistics(boolean shortStatistics) {
        isShortStatistics = shortStatistics;
    }
    public boolean isAppendMode() {
        return isAppendMode;
    }
    public void setAppendMode(boolean appendMode) {
        isAppendMode = appendMode;
    }
    public List<String> getInputFiles() {
        return inputFiles;
    }
    public void setInputFiles(List<String> inputFiles) {
        this.inputFiles = inputFiles;
    }
}
