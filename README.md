Service-Loader on Spring-boot
===

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


    


    


