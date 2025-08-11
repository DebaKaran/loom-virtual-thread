package com.dk.concurrent.threadfactory2.test;

import com.dk.concurrent.threadfactory2.runner.AutoRestartHandler;
import com.dk.concurrent.threadfactory2.runner.TaskRunner;
import com.dk.concurrent.threadfactory2.tasks.DemoTask;
import com.dk.concurrent.threadfactory2.tasks.FailingTask;

public class TaskRunnerTest {
    public static void main(String[] args) {
        TaskRunner runner = new TaskRunner();
        // Submit some normal tasks
        for (int i = 1; i <= 5; i++) {
            runner.submitTask(new AutoRestartHandler(new DemoTask(i), runner));
        }

        // Submit failing tasks
        for (int i = 1; i <= 3; i++) {
            runner.submitTask(new AutoRestartHandler(new FailingTask(i), runner));
        }

        // Let them run for a bit
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Show stats
        System.out.println(runner.getTask());;

        // Shutdown
        runner.shutdown();
    }
}
