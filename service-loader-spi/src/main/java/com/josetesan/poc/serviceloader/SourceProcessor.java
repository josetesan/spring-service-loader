package com.josetesan.poc.serviceloader;

public interface SourceProcessor {

    /**
     *
     * @param message
     * @return
     */
    String handleMessage();

    /**
     *
     * @param type
     * @return
     */
    boolean doesHandle(Integer type);


}
