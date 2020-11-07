package jl.multithread;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinDemo {

    static List<Structure> config = new ArrayList<>();

    static {
        config.add(new Structure());
    }

    public static void main(String[] args) {
        
        test();
    }

    private static void test() {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        for (int i = 0; i < 100; i++) {
            final int a = i;
            ForkJoinTask<?> submit = pool.submit(() -> System.out.println("index:" + a));
            ForkJoinTask<?> fork = submit.fork();
            ForkJoinTask<?> fork1 = fork.fork();
        }
    }

    private List<Integer> get(String a, String b) {
        for (Structure structure : config) {
            if (Objects.equal(a, structure.a) && Objects.equal(b, structure.b)) {
                return structure.c;
            }
        }
        return Collections.emptyList();
    }

    enum A {
        T
    }

    static class Structure {
        private String a;
        private String b;
        private List<Integer> c;
    }
}
