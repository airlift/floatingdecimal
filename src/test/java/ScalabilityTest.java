import io.airlift.floatingdecimal.FloatingDecimal;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class ScalabilityTest
{
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        if (FloatingDecimal.isPatchInstalled()) {
            System.out.println("***** Patch is installed *****");
        }
        else {
            System.out.println("***** Patch is NOT installed *****");
        }

        ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorCompletionService<Long> completion = new ExecutorCompletionService<>(executor);

        int seconds = 3;
        for (int run = 0; run < 2; run++) {

            System.out.println("=== Run: " + run);
            for (int threads = 1; threads <= Runtime.getRuntime().availableProcessors(); threads++) {
                System.out.flush();
                for (int i = 0; i < threads; i++) {
                    completion.submit(new Parser(TimeUnit.SECONDS.toNanos(3)));
                }

                long count = 0;
                for (int i = 0; i < threads; i++) {
                    count += completion.take().get();
                }

                System.out.println(format("Threads: %s, parse/s: %s", threads, count / seconds));
            }
            System.out.println();
        }

        executor.shutdownNow();
    }

    private static class Parser implements Callable<Long>
    {
        private final long maxNanos;

        private Parser(long maxNanos) { this.maxNanos = maxNanos; }

        @Override
        public Long call() throws Exception {
            long count = 0;

            long start = System.nanoTime();
            while (System.nanoTime() - start < maxNanos) {
                Double.parseDouble("1.0E100");
                ++count;
            }

            return count;
        }
    }
}
