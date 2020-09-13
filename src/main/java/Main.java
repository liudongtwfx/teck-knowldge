import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static javax.swing.SwingConstants.CENTER;

public class Main {
    private static final String NAME = "Main";

    public static void main(String[] args) {
        test();
        JButton button = new JButton();
        button.setLayout(new GridLayout(0, 1));
        button.add(new JLabel("Delete All Files", CENTER));
        button.add(new JCheckBox("Are you sure?"));
        button.setVisible(true);
        button.show();
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

