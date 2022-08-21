package com.tilmeez.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {

    @Autowired
    private FortuneService fortuneService;

    // define default constructor

    public TennisCoach() {
        System.out.println(">> TennisCoach: Inside default constructor");
    }

    // define  a setter method
/*    @Autowired
    public void setFortuneService(FortuneService fortuneService) {
        System.out.println(">> TennisCoach: Inside setFortuneService() method");
        this.fortuneService = fortuneService;
    }*/
    /*    @Autowired
    public TennisCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }*/
/*    @Autowired
    public void doSomeCrazyStuff(FortuneService fortuneService){
        System.out.println(">> TennisCoach: Inside doSomeCrazyStuff() method");
        this.fortuneService = fortuneService;
    }*/

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
