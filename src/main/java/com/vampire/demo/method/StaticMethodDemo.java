package com.vampire.demo.method;
import	java.util.function.BiFunction;
import	java.util.function.Function;
import java.util.function.Consumer;
import	java.util.function.Supplier;

/**
 * 静态方法
 * staticClass::staticMethod
 * () -> staticClass.staticMethod();
 */
public class StaticMethodDemo {

    public static String first() {
        System.out.println("first method invoke");
        return "first";
    }

    public static void third(String str) {
        System.out.println("StaticMethodDemo third method invoke");
        System.out.println("StaticMethodDemo str: " + str);
    }

    public static String five(String str) {
        System.out.println("five method invoke");
        return "Five str: " + str + ", " + str.toUpperCase();
    }

    public static String seven(String a, String b) {
        return "Seven str: " + a + ", " + b;
    }

    public static void main(String[] args) {

        supplierTest();

        System.out.println("--------------------------------------------");

        consumerTest();

        System.out.println("--------------------------------------------");

        functionTest();

        System.out.println("--------------------------------------------");

        bifunctionTest();
    }

    /**
     * 两个输入一个输出
     */
    public static void bifunctionTest() {

        BiFunction<String, String, String> bf1 = (a, b) -> StaticMethodDemo.seven(a, b);
        System.out.println(bf1.apply("hello", "world bf1"));

        System.out.println();

        BiFunction < String, String, String> bf2 = StaticMethodDemo::seven;
        System.out.println(bf2.apply("hello", "world bf2"));
    }

    /**
     * 有输入有输出
     */
    public static void functionTest() {

        Function<String, String> f1 = str -> StaticMethodDemo.five(str);
        System.out.println(f1.apply("hello f1"));

        Function<String, String> f2 = StaticMethodDemo::five;
        System.out.println(f2.apply("hello f2"));

        System.out.println();

        Function<String, String> f3 = str -> Func.six(str);
        System.out.println(f3.apply("hello f3"));

        Function<String, String> f4 = Func::six;
        System.out.println(f4.apply("hello f4"));
    }

    /**
     * 有输入无输出
     */
    public static void consumerTest() {

        Consumer<String> c1 = str -> StaticMethodDemo.third(str);
        c1.accept("hello c1");

        Consumer<String> c2 = StaticMethodDemo::third;
        c2.accept("hello c2");

        System.out.println();

        Consumer<String> c3 = str -> ConFun.four(str);
        c3.accept("hello c3");

        Consumer<String> c4 = ConFun::four;
        c4.accept("hello c4");
    }

    /**
     * 无输入有输出
     */
    public static void supplierTest() {

        Supplier<String> s1 = () -> StaticMethodDemo.first();
        System.out.println(s1.get());

        Supplier<String> s2 = StaticMethodDemo::first;
        System.out.println(s2.get());

        System.out.println();

        Supplier<String> s3 = () -> Fun.second();
        System.out.println(s3.get());

        Supplier<String> s4 = Fun::second;
        System.out.println(s4.get());
    }
}

class Fun {
    public static String second() {
        System.out.println("second method invoke");
        return "second";
    }
}

class ConFun {
    public static void four(String str) {
        System.out.println("ConFun four method invoke");
        System.out.println("ConFun str: " + str);
    }
}

class Func {
    public static String six(String str) {
        System.out.println("six method invoke");
        return "Six str: " + str + ", " + str.toUpperCase();
    }
}