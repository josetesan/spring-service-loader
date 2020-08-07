package com.josetesan.poc.serviceloader;

import com.josetesan.poc.serviceloader.config.SourceProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class App implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private SourceProcessorService sourceProcessorService;

    @Override
    public void run(String... args) throws Exception {

        while (true) {
            int type = Integer.parseInt(Arrays.asList(args).get(0));
            log.info("{}", sourceProcessorService.handleEvent(type));
            TimeUnit.SECONDS.sleep(5);
        }
    }
}




