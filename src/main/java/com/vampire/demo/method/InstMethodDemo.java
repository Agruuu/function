package com.vampire.demo.method;
import	java.util.function.Consumer;
import java.util.function.Function;
import	java.util.function.Supplier;

/**
 * 实例方法引用
 * new instClass()::instMethod
 * () -> new instClass().instMethod
 */
public class InstMethodDemo {

    public String getSomething() {
        System.out.println("getSomething invoke");
        return "hello";
    }

    public void put(String string) {
        System.out.println("put invoke");
        System.out.println("Put: " + string);
    }

    public String getAndPut(String str) {
        System.out.println("getAndPut invoke");
        return "getAndPut: " + str + ", " + str.toUpperCase();
    }

    public static void main(String[] args) {

        supplierTest();

        System.out.println("---------------------------------");

        consumerTest();

        System.out.println("---------------------------------");

        functionTest();
    }

    public static void supplierTest() {

        Supplier<String> s1 = () -> new InstMethodDemo().getSomething();
        System.out.println(s1.get());

        Supplier<String> s2 = new InstMethodDemo()::getSomething;
        System.out.println(s2.get());

        InstMethodDemo inst = new InstMethodDemo();
        Supplier<String> s3 = inst::getSomething;
        System.out.println(s3.get());
    }

    public static void consumerTest() {

        Consumer<String> c1 = a -> new InstMethodDemo().put(a);
        c1.accept("hello consumer1");

        Consumer<String> c2 = new InstMethodDemo()::put;
        c2.accept("hello consumer2");
    }

    public static void functionTest() {

        Function<String, String> f1 = str -> new InstMethodDemo().getAndPut(str);
        System.out.println(f1.apply("hello function1"));

        Function<String, String> f2 = new InstMethodDemo()::getAndPut;
        System.out.println(f2.apply("hello function2"));
    }
}
