package jl.gc;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class WeakReferenceTest {
    public static void main(String[] args) {
        WeakReference<Apple> weakReference = new WeakReference<>(new Apple("green-apple"));

        System.gc();

        if (weakReference.get() == null) {
            System.out.println("GC remove weakReference!");
        } else {
            System.out.println("weakReference still alive");
        }

        Map<String, WeakReference<Apple>> appleMap = new HashMap<>();
        appleMap.put("green-apple", new WeakReference<>(new Apple("green-apple")));

        System.gc();
        if (appleMap.get("green-apple").get() == null) {
            System.out.println("GC remove weakReference!");
        } else {
            System.out.println("weakReference still alive");
        }
    }

    public void weakRefNotRemoved() {
        Apple apple = new Apple("green-apple");
        WeakReference<Apple> weakReference = new WeakReference<>(apple);

        System.gc();

        if (weakReference.get() == null) {
            System.out.println("GC remove weakReference!");
        } else {
            System.out.println("weakReference still alive");
        }
    }

    public static class Apple {

        private String name;

        public Apple(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("Apple： " + name + " finalized。");
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "name='" + name + '\'' +
                    '}' + ", hashCode:" + this.hashCode();
        }
    }

}
