package design.pattern.create.builder;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Calendar;

public class Builder {
    public static void main(String[] args) {
        Child child = Child.builder().a("testA").b("testB").build();
        System.out.println(child);
        a();
    }

    @AllArgsConstructor
    public static class Parent {
        private final String a;
    }

    @ToString(callSuper = true)
    public static class Child extends Parent {
        private final String b;

        @lombok.Builder
        private Child(String a, String b) {
            super(a);
            this.b = b;
        }
    }

    private static void a() {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
    }
}
