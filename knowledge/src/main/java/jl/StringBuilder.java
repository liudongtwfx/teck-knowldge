package jl;

import jl.usage.classloader.MyClassloader;

public class StringBuilder {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader my = new MyClassloader("/", "");
        Class<?> aClass = my.loadClass("java.lang.MyString");
        concatStr();
        concatStrWithSb();
    }

    private static void concatStr() {
        String s = "";
        for (int i = 0; i < 100000; i++) {
            s += 'a';
        }
    }

    private static void concatStrWithSb() {
        java.lang.StringBuilder s = new java.lang.StringBuilder();
        for (int i = 0; i < 100000; i++) {
            s.append('a');
        }
    }
}
