package com.dk.concurrent.inheritablethreadlocal;

public class CustomInheritableThreadLocal extends InheritableThreadLocal<String>{

    @Override
    protected String childValue(String parentValue) {
        return parentValue + " (modified in child)";
    }
}
