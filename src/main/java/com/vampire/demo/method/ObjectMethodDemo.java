package com.vampire.demo.method;
import	java.nio.file.SimpleFileVisitor;
import	java.awt.Toolkit;

import java.io.Closeable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 对象方法引用
 */
public class ObjectMethodDemo {

    public static void main(String[] args) {

        Consumer<Too> c1 = (Too too) -> new Too().foo();
        c1.accept(new Too());

        Consumer<Too> c2 = Too::foo;
        c2.accept(new Too());

        BiConsumer<Too2, String> bc1 = (too2,str) -> new Too2().fo(str);
        bc1.accept(new Too2(), "abc");

        BiConsumer<Too2, String> bc2 = Too2::fo;
        bc2.accept(new Too2(), "abc");

        BiFunction<Prod, String, String> bf1 = (prod, str1) -> new Prod().ohh(str1);
        bf1.apply(new Prod(), "abc");

        BiFunction<Prod, Prod, Integer> bf2 = Prod::fun;
        bf2.apply(new Prod(), new Prod());

        /**
         * 不能对象方法引用
         */
        Execute ex1 = (name, size) -> name.equalsIgnoreCase(size);
        Execute ex2 = String::equalsIgnoreCase;

        Exc e1 = (p, name, size) -> new Prod().funcst(p, name, size);
//        Exc e2 = Prod::funcst;
    }



    /**
     * 抽象方法没有输入参数
     * 不能使用对象方法引用
     * 比如说，如下函数式接口
     */
    public void notAccept() {
        Runnable run = () -> {};
        Closeable c = () -> {};
        Supplier<String> s = () -> "";
    }
}

interface Exc {
    void funcst(Prod p, String name, String size);
}

interface Execute {

    void run(String name, String size);
}

class Prod {

    public void funcst(Prod p, String name, String size) {

    }

    public String ohh(String str) {
        System.out.println("ObjectMethodTest foo invoke");
        return str;
    }

    public Integer fun(Prod str) {
        System.out.println("ObjectMethodTest foo invoke");
        return 1;
    }
}

class Too {

    public void foo() {
        System.out.println("ObjectMethodTest foo invoke");
    }
}

class Too2 {

    public void foo() {
        System.out.println("ObjectMethodTest foo invoke");
    }

    public void fo(String str) {

    }
}
