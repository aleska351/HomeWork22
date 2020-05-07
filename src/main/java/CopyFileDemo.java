import java.util.concurrent.*;

public class CopyFileDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<?> future = executor.submit(new CopyFileTask());
        try {
            future.get(8, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            future.cancel(true);
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("Копирование прервано");
            future.cancel(true);
        }
        shutdownAndAwaitTermination(executor);
        System.out.println("Задача закончена");
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
