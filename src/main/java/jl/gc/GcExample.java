package jl.gc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class GcExample {

    private static final String JVM_ARGS = "-Xms64M\n" +
            "-Xmx64M\n" +
            "-XX:+PrintGCDetails\n" +
            "-verbose:gc\n" +
            "-XX:+PrintHeapAtGC\n" +
            "-XX:SurvivorRatio=8\n" +
            "-XX:+UseConcMarkSweepGC";

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //functionWithStack();
            // List<LargeExample> largeExamples = functionWithReturn();
        }
        demoWithWeakRefrence();
    }

    private static void functionWithStack() {
        List<LargeExample> largeExamplee = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeExamplee.add(new LargeExample(i, String.valueOf(i)));
        }
    }

    private static List<LargeExample> functionWithReturn() {
        List<LargeExample> largeExample = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeExample.add(new LargeExample(i, String.valueOf(i)));
        }
        return largeExample;
    }

    /**
     *
     */
    private static void demoWithWeakRefrence() {
        for (int i = 0; i < 100; i++) {
            WeakReference<BigArray> bigArrayWeakReference = new WeakReference<>(new BigArray());

        }
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class LargeExample {
        private Integer a;
        private String name;

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
//            System.out.println(name + " is being finalized by gc");
        }
    }


    private static class BigArray {
        private final int[] nums = new int[1024 * 1024 * 10];
    }
}
