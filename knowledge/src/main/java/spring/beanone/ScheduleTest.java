package spring.beanone;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledFuture;

@Slf4j
@Component
public class ScheduleTest implements CommandLineRunner {
    private TaskScheduler taskScheduler;

    @Override
    public final void run(String... args) {
        new Thread(new ScheduleThread()).start();
    }


    private class ScheduleThread implements Runnable {

        @Override
        public final void run() {
            ScheduledFuture<?> future = taskScheduler.scheduleAtFixedRate(() -> log.info("hello world"), Instant.now(),
                    Duration.of(1, ChronoUnit.SECONDS));
            try {
                log.info("{}", future.get());
            } catch (Exception e) {
                log.error("exception", e);
            }
        }
    }
}
