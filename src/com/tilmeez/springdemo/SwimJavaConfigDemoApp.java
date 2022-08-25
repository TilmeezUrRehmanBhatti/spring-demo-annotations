package com.tilmeez.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SwimJavaConfigDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SportConfig.class);

        // get the bean from spring container
        SwimCoach theCoach = context.getBean("swimCoach", SwimCoach.class);

        // call a method on bean
        System.out.println(theCoach.getDailyWorkout());

        // get method to get daily fortune
        System.out.println(theCoach.getDailyFortune());

        // call new swim coach methods ... has the props values injection
        System.out.println("email:" + theCoach.getEmail());
        System.out.println("team:" + theCoach.getTeam());

        // close the context
        context.close();

    }
}




























