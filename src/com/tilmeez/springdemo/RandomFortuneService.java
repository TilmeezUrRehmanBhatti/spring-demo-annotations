package com.tilmeez.springdemo;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomFortuneService implements FortuneService{

    // create an array of String
    private String[] data = {
            "Beware of the wolf in sheep's cloth",
            "The journey is the reward",
            "Learning java is fun"
    };

    // create a Random generator
    private Random myRandom = new Random();

    @Override
    public String getFortune() {
        // pick a random String  from the array
        int index = myRandom.nextInt(data.length);

        String theFortune = data[index];

        return theFortune;
    }
}
