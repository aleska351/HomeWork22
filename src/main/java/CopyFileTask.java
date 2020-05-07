import java.io.*;
import java.time.Duration;
import java.time.Instant;

public class CopyFileTask implements Runnable{
    @Override
    public void run() {
        copyBufferedBuffer("E:\\movies\\Holop.avi", "E:\\movies\\copy.avi");
    }
        private static void copyBufferedBuffer(String inputFile, String outputFile) {
            long start = System.nanoTime();
            try (InputStream input = new BufferedInputStream(new FileInputStream(inputFile));
                 OutputStream output = new BufferedOutputStream(new FileOutputStream(outputFile))) {

                byte[] buffer = new byte[8192];
                while (!Thread.currentThread().isInterrupted()) {
                    int readCount = input.read(buffer);
                    if (readCount != -1) {
                        output.write(buffer, 0, readCount);
                    } else return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                long finish = System.nanoTime();
                System.out.println(String.format("Время выполнения %.0f s", (finish-start )/1_000_000_000f ));
            }
        }
    }
