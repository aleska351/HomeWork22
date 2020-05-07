public class Stopwatch implements Runnable {
    private long number;

    public Stopwatch(long number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i <= number; i++) {
            System.out.println("Tik "+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Sleep was interrupted");
                return;
            }
        }
    }
}