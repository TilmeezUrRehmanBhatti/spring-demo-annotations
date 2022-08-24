package com.tilmeez.springdemo;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class FileFortuneService implements FortuneService{

    private String fileName = "src/fortune-data.txt";
    private List<String> theFortune;

    // create a random number generator
    private Random myRandom = new Random();

    public FileFortuneService() {

        File theFile = new File(fileName);

        System.out.println("Reading fortune from the file: " + theFile);
        System.out.println("File exists: " + theFile.exists());

        //initialized the list
        theFortune = new ArrayList<>();

        // read the fortune from file
        try (BufferedReader br = new BufferedReader(
                new FileReader(theFile))) {
            String tempLine;
            while ((tempLine = br.readLine()) != null) {
                theFortune.add(tempLine);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getFortune() {
        // pick a random string from array
        int index = myRandom.nextInt(theFortune.size());

        String tempFortune = theFortune.get(index);

        return tempFortune;
    }
}
