package com.dk.concurrent.startathread;

import java.util.List;

public class MultiplicationTest {
    public static void main(String[] args) {
        Tables tables = new Tables(10);
        List<String> result = tables.getMultiplicationTable();

        result.stream().forEach(System.out :: println);
    }
}
