package jl.gc;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * gc参数详解  https://www.jianshu.com/p/74d126dd5544
 * 垃圾回收器 https://www.cnblogs.com/chenpt/p/9803298.html
 * <p></p>
 * https://docs.oracle.com/javase/9/gctuning/concurrent-mark-sweep-cms-collector.htm#JSGCT-GUID-FF8150AC-73D9-4780-91DD-148E63FA1BFF
 */
public class GcExample {

    private static final String JVM_ARGS = "-Xms64M\n" +
            "-XX:NewSize=16M\n" +
            "-XX:MaxNewSize=32M\n" +
            "-Xmx64M\n" +
            "-XX:+PrintGCDetails\n" +
            "-verbose:gc\n" +
            "-XX:+PrintHeapAtGC\n" +
            "-XX:SurvivorRatio=8\n" +
            "-XX:+UseG1GC\n" + //-XX:+UseConcMarkSweepGC
            "-XX:+PrintTenuringDistribution";//对象晋升的日志

    public static void main(String[] args) {
        System.out.println(JVM_ARGS);
        System.out.println(16 * 1024 * 1024);
        System.out.println(1024 * 1024);
        alloc();
        Map<Integer, WeakReference<BigArray>> integerWeakReferenceMap = demoWithWeakReferenced();
        for (int i = 0; i < 10; i++) {
            //functionWithStack();
            new Thread(() -> {
                while (true) {
                    demoWithWeakReferenced();
                }
            }).start();
        }
    }

    private static void alloc() {
        int[] nums = new int[1024 * 10];

    }

    private static void functionWithStack() {
        List<LargeExample> largeExamples = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeExamples.add(new LargeExample(i, String.valueOf(i), new String(new char[1024 * 10])));
        }
    }

    private static List<LargeExample> functionWithReturn() {
        List<LargeExample> largeExample = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeExample.add(new LargeExample(i, String.valueOf(i), new String(new char[1024 * 10])));
        }
        return largeExample;
    }

    /**
     * 弱引用也是用来描述非必需对象的，当JVM进行垃圾回收时，无论内存是否充足，都会回收被弱引用关联的对象。
     * 一般在弱引用的同时，这个对象可能也被强引用了。如果这个强引用消失了，系统就开始回收弱引用。
     *
     * @return
     */
    private static Map<Integer, WeakReference<BigArray>> demoWithWeakReferenced() {
        Map<Integer, WeakReference<BigArray>> map = new HashMap<>();
        for (int i = 0; i < 1000000000; i++) {
            map.put(i, new WeakReference<>(new BigArray(i)));
        }
        return map;
    }

    /**
     * 内存不够的时候回收
     */
    private static void demoWithSoftReferenced() {
        Map<Integer, SoftReference<BigArray>> bigArrayWeakReferenceMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            SoftReference<BigArray> bigArrayWeakReference = new SoftReference<>(new BigArray(i));
            bigArrayWeakReferenceMap.put(i, bigArrayWeakReference);
            System.out.println(bigArrayWeakReference.get().toString());
        }
    }


    @Data
    @AllArgsConstructor
    private static class LargeExample {
        private Integer a;
        private String name;
        private String value;
    }


    private static class BigArray {
        private static final int[] nums = new int[1024 * 1024];
        private final int index;

        BigArray(int index) {
            this.index = index;
        }

        /**
         * 当对象变成(GC Roots)不可达时，GC会判断该对象是否覆盖了finalize方法，若未覆盖，则直接将其回收。否则，若对象未执行过finalize方法，
         * 将其放入F-Queue队列，由一低优先级线程执行该队列中对象的finalize方法。执行finalize方法完毕后，GC会再次判断该对象是否可达，若不可达，则进行回收，否则，对象“复活”。
         * 参考：https://www.cnblogs.com/fnlingnzb-learner/p/7283867.html
         *
         * @throws Throwable
         */
        @Override
        public String toString() {
            return String.valueOf(index);
        }
    }
}
