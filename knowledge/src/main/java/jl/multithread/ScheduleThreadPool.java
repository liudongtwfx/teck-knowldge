package jl.multithread;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduleThreadPool {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> log.info("runner,current:{}", System.currentTimeMillis() / 1000), 1, 1, TimeUnit.SECONDS);
    }
}
