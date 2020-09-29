import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Main {
    private static final String NAME = "Main";

    public static void main(String[] args) {
        test();
        for (int i = 0; i < 100; i++) {
            System.out.println("args = " + Arrays.deepToString(args));
            log.info("test");
        }
    }

    private static void test() {
        BetterArrayList<String> ls = new BetterArrayList<>(new String[0]);
        ls.add("1");
        ls.add("2");
        String[] objects = ls.toArray();
        Arrays.stream(objects).forEach(System.out::println);
    }

    private static class BetterArrayList<E> extends ArrayList<E> {
        private final E[] seedArray;

        public BetterArrayList(E[] seedArray) {
            this.seedArray = seedArray;
        }

        @Override
        public E[] toArray() {
            return seedArray;
        }
    }
}

