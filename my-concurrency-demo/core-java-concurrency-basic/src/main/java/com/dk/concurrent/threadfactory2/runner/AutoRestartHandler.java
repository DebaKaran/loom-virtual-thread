package com.dk.concurrent.threadfactory2.runner;

public class AutoRestartHandler implements Runnable {

    private Runnable task;
    private TaskRunner taskRunner;
    private final int attempt;
    private final int maxAttempt;

    public AutoRestartHandler(Runnable task, TaskRunner runner, int attempt, int maxAttempt) {
        this.task = task;
        this.taskRunner = runner;
        this.attempt = attempt;
        this.maxAttempt = maxAttempt;
    }

    public AutoRestartHandler(Runnable task, TaskRunner runner) {
        this(task, runner, 1, 3); // Default: retry up to 3 times
    }

    @Override
    public void run() {
        try {
            task.run();
        } catch (Exception e) {
            System.err.println("Task failed (attempt " + attempt + "): " + e.getMessage());
            e.printStackTrace();
            if(attempt <maxAttempt) {
                System.out.println("Restarting task (attempt " + (attempt + 1) + ")...");
                taskRunner.incrementRestarts();
                taskRunner.submitTask(new AutoRestartHandler(task, taskRunner, attempt + 1, maxAttempt));
            } else {
                System.out.println("Task reached max retry limit. Not restarting.");
            }

        }
    }
}
