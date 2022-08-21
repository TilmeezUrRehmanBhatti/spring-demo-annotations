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

# Spring Configuration with Java Annotations - Dependency Injection


**What is Spring AutoWiring?**
+ For dependency injection, Spring can use auto wiring
+ Spring will look for a class that _matches_ the property
    + _matches by type:_ class or interface
+ Spring will inject it automatically ... hence it is autowired


**Autowiring Example**

<img src="https://user-images.githubusercontent.com/80107049/185609783-bda43b19-d342-466f-8ec1-eec8b65488de.png" width=300 />

+ Injection FortuneService into a Coach implementation
+ Spring will scan @Components
+ Anyone implements FortuneService interface???
+ If so, let's inject them. For example: _HappyFortuneService_


**Autowiring Injection Types**
+ Constructor Injection
+ Setter Injection
+ Field Injection


**Development Process - Constructor Injection**
1. Define the dependency class and interface
2. Create a constructor in class for injection
3. Configure the dependency injection with @Autowired Annotation


*Step 1:Define the dependency interface and class*
+ So the dependency we have FortuneService its a interface

File:FortuneService.java
```JAVA
public interface FortuneService{
  
  public String getFortune();
}
```
+ implementation of FortuneService interface HappyFortuneService
+ used @Component so spring can auto-scan and find this implementation for spring container

File:HappyFortuneService.java
```JAVA
@Component
public class HappyFortuneService implements FortuneService {
  
  public String getFortune(){
    return "Today is your lucky day!";
  }
}
```

*Step 2:Create a constructor in your class for injections*

File:TennisCoach.java
```JAVA
@Component
public class TennisCoach implements Coach {
  
  private FortuneService fortuneService;
  
  // Constructor with dependency fortuneService
  public TennisCoach(FortuneService theFortuneService){
    fortuneService = theFortuneService
    }
}
```

*Step 3:Configure the dependency injection @AutoWired Annotation*

File:TennisCoach.java
```JAVA
@Component
public class TennisCoach implements Coach {
  
  private FortuneService fortuneService;
  
  // Constructor with dependency fortuneService
  @Autowired
  public TennisCoach(FortuneService theFortuneService){
    fortuneService = theFortuneService
    }
}
```
+ Spring will scan all the @Component and find a bean that implement FortuneService in our case is HappyFortuneService 

### Setter Injection

Inject dependencies by calling setter method(s) on class

**Development Process - Setter Method**
1. Create setter method(s) in your class for injections
2. Configure the dependency injection with @Autowired Annotation

*Step 1:Creating setter method(s) in your class for injection*
File:TennisCoach.java
```JAVA
@Component 
public class TennisCoach implements Coach {
  
  private FortuneService fortuneService;
  
  public TennisCoach() {
  }
  
  public void setFortuneSerivce(FortuneService fortuneService){
    this.fortuneService = fortuneService;
  }
  ...
}
```

*Step 2:Configure the dependency injection with Autowired Annotation*

File:TennisCoach.java
```JAVA
@Component 
public class TennisCoach implements Coach {
  
  private FortuneService fortuneService;
  
  public TennisCoach() {
  }
  
  @Autowired
  public void setFortuneSerivce(FortuneService fortuneService){
    this.fortuneService = fortuneService;
  }
  ...
}
```

> Injection dependencies by calling ANY method on class *Simply by: `@Autowired`*

*Step 2:Configure the dependency injection with Autowired Annotation*

File:TennisCoach.java
```JAVA
@Component 
public class TennisCoach implements Coach {
  
  private FortuneService fortuneService;
  
  public TennisCoach() {
  }
  
  @Autowired
  public void doSomeCrazyStuff(FortuneService fortuneService){
    this.fortuneService = fortuneService;
  }
  ...
}
```
### Field Injection

<center><span style="color:blue"  align=center>Inject dependencies by setting field values on class directly <br/> (even private field) </span></center>
<center><span style="color:red"  align=center>Accomplished by using Java Reflection </span></center>

**Development Process - Filed Injection**
1. Configure the dependency injection with Autowired Annotation
  * Applied directly to the field
  * No need for setter method

*Step 1:Configure the dependency injection with Autowired Annotation*
File:TennisCOach.java
```JAVA
public class TennisCoach implements Coach {
  
  @Autowired
  private FortuneService fortuneService;
  
  public TennisCoach() {
  }
  
  // no need for setter method 
  ...
}
```

**Which Injection Type Should You Use?**    
🔴IMPORTANT❗**Choose a style** Stay consistent in your project from one of Spring injection type because we will get the same functionality across the board.