import java.util.concurrent.*;

public class StopwatchDemo {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future = executor.submit(new Stopwatch(15));
        try {
            future.get(10, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            future.cancel(true);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("Task has timed out");
            future.cancel(true);
        }
        shutdownAndAwaitTermination(executor);
        System.out.println("Task has completed");
    }

    private static void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
