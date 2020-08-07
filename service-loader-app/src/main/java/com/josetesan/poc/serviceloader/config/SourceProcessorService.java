package com.josetesan.poc.serviceloader.config;

import com.josetesan.poc.serviceloader.SourceProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ServiceLoader;

@Service
@Slf4j
public class SourceProcessorService {

    private final Loader loader;

    public SourceProcessorService(Loader loader) {
        this.loader = loader;
    }

    @Scheduled(fixedRate = 10_000)
    public void refresh() {
        log.info("Reloading ....");
        loader.reload();
    }

    public String handleEvent(int type) {
        SourceProcessor sourceProcessor = loader
                    .retrieve()
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
