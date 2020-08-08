# Service-Loader on Spring-boot

## HOW IT SHOULD BE ( Java8 or Less )
0. Build it

   ```mvn package```

1. Run it

    Go to service-loader-app/target and run
    
    ``java -jar service-loader-app-0.0.1-SNAPSHOT.jar 1``
    
    You will see the following output
    
```    
 20:12:09.201  INFO 13016 --- [main] c.j.p.s.config.SourceProcessorService    : Reloading ....
 20:12:09.201  INFO 13016 --- [main] c.j.poc.serviceloader.config.Loader      : 0
 20:12:09.202  INFO 13016 --- [main] com.josetesan.poc.serviceloader.App      : Null Handler
 20:12:14.203  INFO 13016 --- [main] com.josetesan.poc.serviceloader.App      : Null Handler
```

2. Deploy new instances

  **Removed in java9+**
   
   Copy `service-loader-impl1/target/*.jar` to configured `java.ext.dir` folder. 
   
   You *should* see
   
```   
     20:14:09.201  INFO 13016 --- [main] c.j.p.s.config.SourceProcessorService    : Reloading ....
     20:14:09.201  INFO 13016 --- [main] c.j.poc.serviceloader.config.Loader      : 1
     20:14:09.202  INFO 13016 --- [main] com.josetesan.poc.serviceloader.App      : Hello from Impl1
     20:14:14.203  INFO 13016 --- [main] com.josetesan.poc.serviceloader.App      : Hello from Impl1
```

3. Deploy new instances, 2

   Copy `service-loader-impl2/target/*.jar` to configured `java.ext.dir` folder.
   
   You *should* see
   
```   
     20:16:09.201  INFO 13016 --- [main] c.j.p.s.config.SourceProcessorService    : Reloading ....
     20:16:09.201  INFO 13016 --- [main] c.j.poc.serviceloader.config.Loader      : 2
     20:16:09.202  INFO 13016 --- [main] com.josetesan.poc.serviceloader.App      : Hello from Impl1
     20:16:14.203  INFO 13016 --- [main] com.josetesan.poc.serviceloader.App      : Hello from Impl1
```

4. Remove instance 1
 
   Remove `service-loader-impl1/target/*.jar` from configured `java.ext.dir` folder.
   
   You *should* see
   
```   
     20:18:09.201  INFO 13016 --- [main] c.j.p.s.config.SourceProcessorService    : Reloading ....
     20:18:09.201  INFO 13016 --- [main] c.j.poc.serviceloader.config.Loader      : 1
     20:18:09.202  INFO 13016 --- [main] com.josetesan.poc.serviceloader.App      : Null Handler
     20:18:14.203  INFO 13016 --- [main] com.josetesan.poc.serviceloader.App      : Null Handler
``` 

## How it is ( Java9+ ) 

0. Build it

   ```mvn package```

1. Run it

  ```
   jar xfv target/*.jar
   java -cp BOOT-INF/*:BOOT-INF/lib/*:. org.springframework.boot.loader.JarLauncher <parameters>
 ```
 The folder should look like :
 ```

├── BOOT-INF
│ ├── classes
│ │ └── com
│ │     └── josetesan
│ │         └── poc
│ │             └── serviceloader
│ │                 ├── App.class
│ │                 ├── SourceProcessorService$1.class
│ │                 └── SourceProcessorService.class
│ ├── classpath.idx
│ └── lib
│     ├── spring-boot-2.3.2.RELEASE.jar
      .
      .
      .

├── META-INF
│ ├── MANIFEST.MF
│ ├── maven
│ │ └── com.josetesan.poc
│ │     └── service-loader-app
│ │         ├── pom.properties
│ │         └── pom.xml
└── org
    └── springframework
        └── boot
            └── loader
              .
              .
              .

                
```


You'll see

```
 11:13:31.118  INFO 8318 --- [   scheduling-1] c.j.p.s.SourceProcessorService           : Reloading ....
 11:13:31.151  INFO 8318 --- [           main] c.j.p.s.SourceProcessorService           : Found 0
 11:13:31.151  INFO 8318 --- [           main] com.josetesan.poc.serviceloader.App      : Null Handler
 11:13:36.152  INFO 8318 --- [           main] c.j.p.s.SourceProcessorService           : Found 0

```
2. Deploy new instance

 - Copy your implementation's classes folder to current workdir
 - Copy your implementation's `META-INF/services` folder to current workdir,

 
You'll see

```
├── BOOT-INF
│ ├── classes
│ │ └── com
│ │     └── josetesan
│ │         └── poc
│ │             └── serviceloader
│ │                 ├── App.class
│ │                 ├── SourceProcessorService$1.class
│ │                 └── SourceProcessorService.class
│ ├── classpath.idx
│ └── lib
│     ├── spring-boot-starter-2.3.2.RELEASE.jar
│     .
│     .
│     .
├── com          <-- My new implementation
│ └── josetesan
│     └── poc
│         └── serviceloader
│             └── Impl1.class
├── META-INF
│ ├── MANIFEST.MF
│ ├── maven
│ │ └── com.josetesan.poc
│ │     └── service-loader-app
│ │         ├── pom.properties
│ │         └── pom.xml
│ └── services
│     └── com.josetesan.poc.serviceloader.SourceProcessor  <--- My new Service !!
└── org
    └── springframework
        └── boot
            └── loader
              .
              .
              .


```

```
 11:13:11.118  INFO 8318 --- [   scheduling-1] c.j.p.s.SourceProcessorService           : Reloading ....
 11:13:11.146  INFO 8318 --- [           main] c.j.p.s.SourceProcessorService           : Found 1
 11:13:11.146  INFO 8318 --- [           main] com.josetesan.poc.serviceloader.App      : Hello from Impl1
```  
   
   

### Et voilá ! Runtime discovery :) ###


    


    


