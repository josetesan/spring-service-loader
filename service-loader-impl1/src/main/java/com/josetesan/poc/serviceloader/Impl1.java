package com.josetesan.poc.serviceloader;

public class Impl1 implements SourceProcessor{

    @Override
    public String handleMessage() {
        return "Hello from Impl1";
    }

    @Override
    public boolean doesHandle(Integer type) {
        return (type % 2 != 0);
    }
}
