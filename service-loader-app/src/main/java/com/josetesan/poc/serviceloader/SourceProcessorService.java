package com.josetesan.poc.serviceloader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ServiceLoader;

@Service
@Slf4j
public class SourceProcessorService {

    private final ServiceLoader<SourceProcessor> serviceLoader;

    public SourceProcessorService() {
        serviceLoader = ServiceLoader.load(SourceProcessor.class);
        log.info("{}", serviceLoader.stream().count());
    }

    @Scheduled(fixedRate = 10_000)
    public void refresh() {
        log.info("Reloading ....");
        serviceLoader.reload();
    }

    public String handleEvent(int type) {

        log.info("Found {}",serviceLoader.stream().count());

        SourceProcessor sourceProcessor = serviceLoader
                    .stream()
                    .filter(p -> p.get().doesHandle(type))
                    .map(ServiceLoader.Provider::get)
                    .findFirst()
                    .orElseGet(() -> new SourceProcessor() {
                        @Override
                        public String handleMessage() {
                            return "Null Handler";
                        }

                        @Override
                        public boolean doesHandle(Integer type1) {
                            return true;
                        }
                    });

        return sourceProcessor.handleMessage();

    }
}
