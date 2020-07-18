package spring.beanone;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@Slf4j
@Component
public class ScheduleTest implements CommandLineRunner {
    @Autowired
    private TaskScheduler taskScheduler;

    @Override
    public void run(String... args) {
        new Thread(new ScheduleThread()).start();
    }


    private class ScheduleThread implements Runnable {

        @Override
        public void run() {
            ScheduledFuture<?> future = taskScheduler.scheduleAtFixedRate(() -> log.info("hello world"), new Date(), 60 * 1000);
            try {
                log.info("{}", future.get());
            } catch (Exception e) {
                log.error("exception", e);
            }
        }
    }
}
