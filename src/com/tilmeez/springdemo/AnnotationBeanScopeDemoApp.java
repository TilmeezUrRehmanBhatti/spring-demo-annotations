package com.tilmeez.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {

    public static void main(String[] args) {
        // load spring config file
        ClassPathXmlApplicationContext context = new
                ClassPathXmlApplicationContext("applicationContext.xml");

        // retrieving a bean from spring container
        Coach theCoach = context.getBean("tennisCoach", Coach.class);


        Coach alphaCoach = context.getBean("tennisCoach", Coach.class);

        // check if they are same
        boolean result = (theCoach == alphaCoach);

        // print out result
        System.out.println("\nPointing to same object: " + result);

        System.out.println("\nMemory location for theCoach: " + theCoach);

        System.out.println("\nMemory location for alphaCoach: " + alphaCoach);

        // close context
        context.close();
    }
}
