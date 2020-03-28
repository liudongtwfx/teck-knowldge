package jl.gc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


/**
 * -Xms64M
 * -Xmx64M
 * -XX:+PrintGCDetails
 * -verbose:gc
 * -XX:+PrintHeapAtGC
 * -XX:SurvivorRatio=8
 */
public class GcExample {
    public static void main(String[] args) {
        List<LargeExample> largeExamplee = new ArrayList<>();

        for (int i = 0; i < 300000; i++) {
            largeExamplee.add(new LargeExample(i, String.valueOf(i)));
            //System.gc();
        }

        for (LargeExample largeExample : largeExamplee) {
            //do nothing
        }

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class LargeExample {
        private Integer a;
        private String name;
    }
}
