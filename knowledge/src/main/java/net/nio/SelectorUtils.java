package net.nio;

import java.nio.channels.Selector;

/**
 * @author <a href="mailto:liudong"
 */
public class SelectorUtils {
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        System.out.println(selector.getClass().getName());
    }
}
