package com.josetesan.poc.serviceloader;

public class Impl2 implements SourceProcessor{

    @Override
    public String handleMessage() {
        return "Hello from Impl2";
    }

    @Override
    public boolean doesHandle(Integer type) {
        return (type % 2 == 0);
    }
}
