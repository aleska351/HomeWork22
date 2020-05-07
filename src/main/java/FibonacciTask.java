import java.math.BigInteger;

public class FibonacciTask implements Runnable {
    BigInteger n;
    BigInteger current = BigInteger.valueOf(1);
    BigInteger previous = BigInteger.valueOf(1);

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            while (!Thread.currentThread().isInterrupted()) {
                n = previous.add(current);
                System.out.print(n + " ");
                previous = current;
                current = n;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("Sleep was interrupted");
                    System.out.println("Previous number Fibonacci " + previous);
                    System.out.println("Next number Fibonacci " + current);
                    return;
                }
            }
        }
    }
}