package design.pattern.create.builder;

import lombok.AllArgsConstructor;

public class Builder {
    public static void main(String[] args) {
        Child child = Child.builder().a("testA").b("testB").build();
        System.out.println(child);
    }

    @AllArgsConstructor
    public static class Parent {
        private String a;
    }

    public static class Child extends Parent {
        private String b;

        @lombok.Builder
        private Child(String a, String b) {
            super(a);
            this.b = b;
        }
    }
}
