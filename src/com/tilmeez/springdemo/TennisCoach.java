package com.tilmeez.springdemo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope("prototype")
public class TennisCoach implements Coach, DisposableBean {

    @Qualifier("fileFortuneService")
    @Autowired
    private FortuneService fortuneService;

    // define default constructor

    public TennisCoach() {
        System.out.println(">> TennisCoach: Inside default constructor");
    }

    // define my ini method
    @PostConstruct
    public void doMyStartupStuff(){
        System.out.println(">> TennisCoach: inside of doMyStartupStuff()");
    }

    // define my destroy method
    @PreDestroy
    public void doMyCleanupStuff(){
        System.out.println(">> TennisCoach: inside of duMyCleanupStuff()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(">> TennisCoach: inside destroy()");
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
