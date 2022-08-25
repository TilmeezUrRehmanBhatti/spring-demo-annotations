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

## Qualifiers for Dependency Injection - Overview
The *@Autowired* annotation is a great way of making the need to inject a dependency in Spring explicit. Although it's useful, there are use cases for which this annotation alone isn't enough for Spring to understand which bean to inject.

By default, Spring resolves autowired entries by type.

**If more than one bean of the same type is available in the container, the framework will throw *NoUniqueBeanDefinitionException****,* indicating that more than one bean is available for autowiring.

By using the *@Qualifier *annotation, we can **eliminate the issue of which bean needs to be injected**.

**Injection Types**

+ Can apply **@Qualifier** annotation to
  + Constructor Injection
  + Setter Injection method
  + Field Injection

**Annotations - Default Bean Names ... and the Special Case**

In general, when using Annotations, for the default bean name, Spring uses the following rule.

*If the annotation's value doesn't indicate a bean name, an appropriate name will be built based on the short name of the class (with the first letter lower-cased).*

For example:

HappyFortuneService --> happyFortuneService

\---

However, for the **special case of when BOTH the first and second characters of the class name are upper case**, then the **name is NOT converted**.

For the case of RESTFortuneService

RESTFortuneService --> RESTFortuneService

*No conversion* since the first two characters are upper case.

Behind the scenes, Spring uses the **Java Beans Introspector** to generate the default bean name. Here's a screenshot of the documentation for the key method.

![image](https://user-images.githubusercontent.com/80107049/186266646-425c7f64-c8f5-4ce1-9372-c44be0f4ad49.png)

Also, here's a link to the documentation.

\- <https://docs.oracle.com/javase/8/docs/api/java/beans/Introspector.html#decapitalize(java.lang.String)>

\---

As always, you can specify a name for your bean.

```JAVA
@Component("foo")
public class RESTFortuneService .... {
    
}
```

Then you can access it using the name of "foo".

**Using @Qualifier with Constructors**

**@Qualifier** is a nice feature, but it is tricky when used with Constructors.

The syntax is much different from other examples and not exactly intuitive.  Consider this the "deep end of the pool" when it comes to Spring configuration LOL :-)

\---

```JAVA
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {

    private FortuneService fortuneService;

    // define a default constructor
    public TennisCoach() {
        System.out.println(">> TennisCoach: inside default constructor");
    }
    
    @Autowired
    public TennisCoach(@Qualifier("randomFortuneService") FortuneService theFortuneService) {

        System.out.println(">> TennisCoach: inside constructor using @autowired and @qualifier");
        
        fortuneService = theFortuneService;
    }
       
    
    /*
    @Autowired
    public void doSomeCrazyStuff(FortuneService theFortuneService) {
        System.out.println(">> TennisCoach: inside doSomeCrazyStuff() method");
        fortuneService = theFortuneService;
    }
    */
    
    /*
    @Autowired
    public TennisCoach(FortuneService theFortuneService) {
        fortuneService = theFortuneService;
    }
    */
    
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

}
```
---
For detailed documentation on using @Qualifier with Constructors, see this link in the Spring Reference Manual

<https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-autowired-annotation-qualifiers>



====

**@Qualifier with Setter Injection**

You can use similar syntax with Setter Injection. You can use @Qualifier in method arguments line, such as
```JAVA
	@Autowired
	public void setFortuneService(@Qualifier("randomFortuneService") FortuneService theFortuneService) {
		System.out.println(">> TennisCoach: inside setFortuneService() method");
		this.fortuneService = theFortuneService;
	}
```
You can also use the @Qualifier above the method name. For example, here's the syntax

```JAVA
  @Autowired
	@Qualifier("randomFortuneService")
	public void setFortuneService(FortuneService theFortuneService) {
		System.out.println(">> TennisCoach: inside setFortuneService() method");
		this.fortuneService = theFortuneService;
	}
```
**How to inject properties file using Java annotations**




**Answer:**

This solution will show you how inject values from a properties file using annotatons. The values will no longer be hard coded in the Java code.

**1. Create a properties file to hold your properties. It will be a name value pair.  **

New text file:  src/sport.properties

```Properties
foo.email=myeasycoach@gmail.com
foo.team=Silly Java Coders
```

Note the location of the properties file is very important. It must be stored in src/sport.properties

**2. Load the properties file in the XML config file.**

File: applicationContext.xml

Add the following lines:

`  <context:property-placeholder location="classpath:sport.properties"/>  `

This should appear just after the \<context:component-scan .../> line

**3. Inject the properties values into your Swim Coach: SwimCoach.java**

```JAVA
@Value("${foo.email}")
private String email;
    
@Value("${foo.team}")
private String team;
```

\---

# Spring Configuration with Java Annotations - Bean Scopes and Lifecycle Methods
**Bean Scope**
+ Scope refers to lifecycle of a bean
+ How long does bean live?
+ How many instances are created?
+ How is the bean shared?

**Default Scope**
<center><span style="color:blue"  align=center>Default Scope is singleton </span></center>

+ Spring Container creates only one instance of the bean, by default
+ It is cached in memory
+ All request for the bean
  + will return a **SHARED** reference to **SAME** bean


<img src="https://user-images.githubusercontent.com/80107049/186634984-0888d759-6e5b-4656-aadb-1857a632571c.png" width=400 />

**Explicitly Specify Bean Scope**
```JAVA
@Component
@Scope("singleton")
public class TennisCoach() implements Coach{
 ... 
}
```

**Prototype Scope Example**
Prototype scope: new object for each request
```JAVA
@Component
@Scope("prototype")
public class TennisCoach() implements Coach{
 ... 
}
```
<img src="https://user-images.githubusercontent.com/80107049/186635143-4a4364e5-9e5a-4d67-98ca-d59974476177.png" width=600 />

**Bean Lifecyle Methods/Hooks**
+ Can add custom code during **bean initialization**
  + Calling custom business logic methods
  + Setting up handles to resources(db, sockets, file etc)


+ Can add custom code during **bean destruction**
  + Calling custom business logic method
  + Clean up handles to resources(db, sockets, files etc)

**Development Process**
1. Define your methods for init and destroy
2. Add annotations: @PostConstruct and @PreDestroy

**Init: method configuration**
```JAVA
@Component
public class TennisCoach implements Coach {
  
  @PostConstruct
  public void doMyStartupStuff() { ... }
  
  ...
    
}
``` 
<center>
Code will execute after constructor <br/>
  <b>and</b><br/>
after injection of dependencies
</center>  

**Destroy: method configuration**
```JAVA
@Component
public class TennisCoach implements Coach {
  
  @PreDestroy
  public void doMyCleanupStuff() { ... }
  
  ...
    
}
``` 
<center>
  Code will execute <b>before</b> <br/>
  bean is destroyed
  </center>

*Special Note about @PostConstruct and @PreDestroy Method Signatures*

**Access modifier**

The method can have any access modifier (public, protected, private)

**Return type**

The method can have any return type. However, "void' is most commonly used. If you give a return type just note that you will not be able to capture the return value. As a result, "void" is commonly used.

**Method name**

The method can have any method name.

**Arguments**

The method can not accept any arguments. The method should be no-arg.



> If you are using Java 9 or higher, then you will encounter an error when using @PostConstruct and @PreDestroy in your code.
***Solution***

1\. Download the *javax.annotation-api-1.3.2.jar* from

<https://search.maven.org/remotecontent?filepath=javax/annotation/javax.annotation-api/1.3.2/javax.annotation-api-1.3.2.jar>

2\. Copy the JAR file to the **lib** folder of your project

Use the following steps to add it to your Java Build Path.

3\. Right-click your project, select **Properties**

4\. On left-hand side, click **Java Build Path**

5\. In top-center of dialog, click **Libraries**

6\. Click **Classpath** and then Click **Add JARs ...**

7\. Navigate to the JAR file **\<your-project>/lib/javax.annotation-api-1.3.2.jar**

8\. Click **OK **then click **Apply and Close**


\---

***In contrast to the other scopes, Spring does not manage the complete lifecycle of a  
prototype bean****: the container instantiates, configures, and otherwise assembles a  
prototype object, and hands it to the client, with no further record of that prototype  
instance.
*

*Thus, although initialization lifecycle callback methods are called on all objects regardless of scope, ****in the case of prototypes, configured destruction lifecycle callbacks are not called****. The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding. *

*To get the Spring container to release resources held by prototype-scoped beans, try using a custom *[*bean post-processor*](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-extension-bpp)*, which holds a reference to beans that need to be cleaned up.
*

This also applies to XML configuration.

\---

***QUESTION: How can I create code to call the destroy method on prototype scope beans***




***ANSWER:***

You can destroy prototype beans but custom coding is required. This examples shows how to destroy prototype scoped beans.

1. Create a custom bean processor. This bean processor will keep track of prototype scoped beans. During shutdown it will call the destroy() method on the prototype scoped beans.
```JAVA
package com.tilmeez.springdemo;

public class MyCustomBeanProcessor implements BeanPostProcessor, BeanFactoryAware, DisposableBean {

    private BeanFactory beanFactory;

    private final List<Object> prototypeBeans = new LinkedList<>();

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        // after startup, keep track of prototype scoped beans.
        // we will need to know who they are for later destruction

        if (beanFactory.isPrototype(beanName)) {
            synchronized (prototypeBeans) {
                prototypeBeans.add(bean);
            }
        }
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws  BeansException{
        this.beanFactory = beanFactory;
    }

    @Override
    public void destroy() throws Exception {

        // loop through the prototype beans and call the destroy() method on each one
        synchronized (prototypeBeans) {
            for (Object bean : prototypeBeans) {
                if (bean instanceof DisposableBean) {
                    DisposableBean disposable = (DisposableBean) bean;
                    try {
                        disposable.destroy();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        prototypeBeans.clear();

    }

}
```
2. The prototype scoped beans MUST implement the DisposableBean interface. This interface defines a "destroy()" method. This method should be used instead of the @PreDestroy annotation.

```JAVA
package com.tilmeez.springdemo;

@Component
@Scope("prototype")
public class TennisCoach implements Coach, DisposableBean {

    @Qualifier("randomFortuneService")
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

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
```
3. In this app, AnnotationDemoApp.java is the main program. TennisCoach.java is the prototype scoped bean. TennisCoach implements the DisposableBean interface and provides the destroy() method. The custom bean processing is handled in the MyCustomBeanProcessor class.


