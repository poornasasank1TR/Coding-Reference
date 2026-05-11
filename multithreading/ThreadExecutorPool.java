package multithreading;

import java.util.concurrent.*;

public class ThreadExecutorPool {

    public static void main(String[] args) {
        // Create example of Thread Executor pool with:
        // - 2 core threads
        // - 4 max threads
        // - queue size of 2
        // - 4 tasks
        int corePoolSize = 2;
        int maxPoolSize = 4;
        int queueSize = 2;
        long keepAliveTime = 10;

        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(queueSize);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                queue,
                new ThreadPoolExecutor.AbortPolicy() // Throws RejectedExecutionException if full
        );

        // Submit 4 tasks
        for (int i = 1; i <= 4; i++) {
            final int taskId = i;
            try {
                executor.submit(() -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Task " + taskId + " started on " + threadName);
                    try {
                        Thread.sleep(2000); // Simulate work
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.println("Task " + taskId + " completed on " + threadName);
                });
                System.out.println("Task " + taskId + " submitted | Active: " + executor.getActiveCount()
                        + " | Queue: " + executor.getQueue().size()
                        + " | Pool Size: " + executor.getPoolSize());
            } catch (RejectedExecutionException e) {
                System.out.println("Task " + taskId + " was REJECTED (queue full and max threads reached)");
            }
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("All tasks finished.");
    }
}
