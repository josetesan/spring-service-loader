package com.josetesan.poc.serviceloader.config;

import com.josetesan.poc.serviceloader.SourceProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ServiceLoader;
import java.util.stream.Stream;

@Component
@Slf4j
public class Loader {

    private final ServiceLoader<SourceProcessor> serviceLoader;

    public Loader() {
      serviceLoader = ServiceLoader.load(SourceProcessor.class);
      log.info("{}", serviceLoader.stream().count());
    }

    public void reload() {
        serviceLoader.reload();
        log.info("{}", serviceLoader.stream().count());
    }

    public Stream<ServiceLoader.Provider<SourceProcessor>> retrieve() {
        return serviceLoader.stream();
    }

}
