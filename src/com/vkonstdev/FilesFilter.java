package com.vkonstdev;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilesFilter {
    private final CommandLineArgs cla;
    private final String[] outputFileNames = {"integers.txt", "floats.txt", "strings.txt"};
    private List<String> integerList = new ArrayList<>();
    private List<String> floatList = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();

    public FilesFilter(CommandLineArgs cla) {
        this.cla = cla;
    }

    public void filter() {
        List<String> inputFiles = cla.getInputFiles();
        if (inputFiles.size() == 0) {
            throw new RuntimeException("Входные файлы отсутствуют");
        }
        try {
            for (String file : inputFiles) {
                readFiles(file);
            }

            writeToFile(cla.getOutputPath(), cla.getFilePrefix() + outputFileNames[0], integerList, cla.isAppendMode());
            writeToFile(cla.getOutputPath(), cla.getFilePrefix() + outputFileNames[1], floatList, cla.isAppendMode());
            writeToFile(cla.getOutputPath(), cla.getFilePrefix() + outputFileNames[2], stringList, cla.isAppendMode());

            if (cla.isShortStatistics()) {
                printShortStatistics();
            } else {
                printFullStatistics();
            }

            System.out.println("Программа выполнена успешно.");

        } catch (IOException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        }
    }

    private void readFiles(String filePath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (isInteger(line)) {
                    integerList.add(line);
                } else if (isFloat(line)) {
                    floatList.add(line);
                } else {
                    if (!"".equals(line)) {
                        stringList.add(line.trim());
                    }
                }
            }
        }
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void writeToFile(String outputPath, String fileName, List<String> data, boolean isAppendMode) throws IOException {
        if (!data.isEmpty()) {
            String filePath = Paths.get(outputPath, fileName).toString();
            FileWriter writer = new FileWriter(filePath, isAppendMode);
            for (String line : data) {
                writer.write(line + "\n");
            }
            writer.close();
        }
    }

    private void printShortStatistics(){
        if (!integerList.isEmpty()) {
            System.out.println("Количество целых чисел: " + integerList.size());
        }
        if (!floatList.isEmpty()){
            System.out.println("Количество вещественных чисел: " + floatList.size());
        }
        if (!stringList.isEmpty()){
            System.out.println("Количество строк: " + stringList.size() + "\n") ;
        }
    }

    private void printFullStatistics(){
        printShortStatistics();

        if (!integerList.isEmpty()) {
            System.out.println("Минимальное целое число: " + integerList.stream().mapToInt(Integer::parseInt).min().getAsInt());
            System.out.println("Максимальное целое число: " + integerList.stream().mapToInt(Integer::parseInt).max().getAsInt());
            System.out.println("Сумма целых чисел: " + integerList.stream().mapToInt(Integer::parseInt).sum());
            System.out.println("Среднее значение целых чисел: " + integerList.stream().mapToInt(Integer::parseInt).average().getAsDouble() + "\n");
        }

        if (!floatList.isEmpty()){
            System.out.println("Минимальное вещественное число: " + floatList.stream().mapToDouble(Double::parseDouble).min().getAsDouble());
            System.out.println("Максимальное вещественное число: " + floatList.stream().mapToDouble(Double::parseDouble).max().getAsDouble());
            System.out.println("Сумма вещественных чисел: " + floatList.stream().mapToDouble(Double::parseDouble).sum());
            System.out.println("Среднее значение вещественных чисел: " + floatList.stream().mapToDouble(Double::parseDouble).average().getAsDouble() + "\n");
        }

        if (!stringList.isEmpty()){
            System.out.println("Длина самой короткой строки: " + stringList.stream().mapToInt(String::length).min().orElse(0));
            System.out.println("Длина самой длинной строки: " + stringList.stream().mapToInt(String::length).max().orElse(0) + "\n");
        }
    }

}
