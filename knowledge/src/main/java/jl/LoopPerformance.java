package jl;

import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoopPerformance {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
            set.add(i);
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        long sum = 0;
        for (Integer integer : list) {
            sum += integer;
        }
        stopWatch.stop();
        System.out.println(stopWatch.getNanoTime());

        stopWatch = new StopWatch();
        stopWatch.start();

        for (Integer integer : set) {
            sum += integer;
        }
        stopWatch.stop();
        System.out.println(stopWatch.getNanoTime());
    }
}
