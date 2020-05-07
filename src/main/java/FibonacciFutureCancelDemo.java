import java.util.concurrent.*;

public class FibonacciFutureCancelDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<?> future = executor.submit(new FibonacciTask());
        try {
            future.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            future.cancel(true);

        } catch (ExecutionException e) {
            e.printStackTrace(); //
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
