package com.dk.concurrent.startathread;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class MultiplicationThreadTest {

    @Test
    void testMultiplicationThreadsOutput() {
        int numberOfTables = 5;
        Tables tables = new Tables(numberOfTables);
        List<String> result = tables.getMultiplicationTable();

        assertNotNull(result);
        assertEquals(numberOfTables * 10, result.size(),
                "There should be 50 multiplication entries");

        assertTrue(result.stream().allMatch(line -> line.startsWith("Thread-")));
        assertTrue(result.stream().allMatch((line -> line.contains(" = "))));

        //Check that threads were actually parallel (rough check via thread names):
        long distinctThreads = result.stream()
                .map(line -> line.split(":")[0])
                .distinct()
                .count();
        assertTrue(distinctThreads > 1 && distinctThreads == numberOfTables,
                "Should involve multiple threads");

    }
}
