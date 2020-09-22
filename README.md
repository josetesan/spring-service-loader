# Service-Loader on Spring-boot

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
### Hot-Deployment 
   
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
And you'll see
```
 11:13:11.118  INFO 8318 --- [   scheduling-1] c.j.p.s.SourceProcessorService           : Reloading ....
 11:13:11.146  INFO 8318 --- [           main] c.j.p.s.SourceProcessorService           : Found 1
 11:13:11.146  INFO 8318 --- [           main] com.josetesan.poc.serviceloader.App      : Hello from Impl1
```  
      
### Et voilá ! Runtime discovery :) ###

### Cold-Deployment
 In this method, you have to STOP the app and restart later once the new providers are deployed, but 
 at least, you can deploy a jar file, without unpacking them.
 
 
  Run your app with
  ```
   java -Dloader.path="plugins/" -jar target/*.jar
  ```

  See the *plugins* extra entry in the classpath ? That's where you must deploy your implementations jar
```
target   
     |── application.jar 
     └── plugins
         ├── service-loader-impl1-0.0.1-SNAPSHOT.jar
         └── service-loader-impl2-0.0.1-SNAPSHOT.jar

```

Remember , *before removing* ANY jar file in the plugins folder, you *HAVE to stop* the application, 
otherwise, it might crash.
You don't need to stop it in case you want to ADD a jar, but it won't pick it up until you restart the app.

That's great, because you can schedule application stops ... 

    


