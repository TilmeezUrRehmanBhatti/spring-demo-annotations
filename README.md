# Spring Configuration with Java Annotations - Inversion of Control
## Annotation Overview - Component Scanning

**What are Java Annotations?**
+ Special labels/markers added to Java classes
+ Provide meta-data about the class
+ Processed at compile time or run-time for special processing

<img src="https://user-images.githubusercontent.com/80107049/185377922-053a6fc3-65de-453b-a7d3-e5d95044b5d4.png" width=80 />


**Annotation Example**

<img src="https://user-images.githubusercontent.com/80107049/185378255-12eeb8dd-f7f6-4268-9928-6face76e9f9e.png" width=500 />


**Why Spring Configuration with Annotations?**
+ XML configuration can be verbose
+ Configure your Spring beans with Annotations
+ Annotations minimize the XML configuration

**Scanning for Component Classes**
+ Spring will scan Java class for special annotations
+ Automatically register the bean in the Spring Container


**Development Process**
1. Enable component scanning in config file
2. Add the @Component Annotation to Java Class
3. Retrieve bean from Spring container

*Step 1:Enable component scanning in Spring config file*
```XML
<beans ...>
    <context:component-scan base-package="com.tilmeez.springdemo" />
</beans>
```
+ Spring will scan the package `base-package="com.tilmeez.springdemo` (recursively)


*Step 2:Add the @Component Annotation to Java class*
<img src="https://user-images.githubusercontent.com/80107049/185377986-b3feb208-2a83-42a1-8de3-8b91233b7515.png" width=600/>

*Step 3:Retrieve bean from Spring container*
```JAVA
Coach theCoach = context.getBean("thatSillyCoach", Coach.class);
```

+ `"thatSillyCoach"` is bean id which is define in step 2 at @Component

## Default Component Names - Overview

**Spring also support Default Bean IDs**
+ Default bean id: the class name, **make first letter lower-case**

<img src="https://user-images.githubusercontent.com/80107049/185380253-4ac91afe-35a5-4dd4-b26c-86d9e38c553e.png"   width=500/>

**Code Example**
<img src="https://user-images.githubusercontent.com/80107049/185380304-449a3fbe-a26d-4a49-9a4a-c4d43c63568b.png" width=500/>