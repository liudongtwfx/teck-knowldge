package jl.usage.classloader;

public class MyObject {
    private int value;
    static {
        System.out.println("MyObject load");
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
