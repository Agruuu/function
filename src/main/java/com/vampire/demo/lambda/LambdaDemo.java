package com.vampire.demo.lambda;
import	java.util.function.BiFunction;
import	java.util.concurrent.Callable;
import java.util.function.Function;

/**
 * Lambda 表达式
 */
public class LambdaDemo {

    public static void main(String[] args) throws Exception {

        functionDemo();
        System.out.println("--------分割线--------");

        Runnable r1 = () -> get();
        r1.run();
        Runnable r2 = () -> exec();
        r2.run();

        Foo f1 = () -> "f1: " + get();
        System.out.println(f1.get());
//        Foo f2 = () -> exec();

        /**
         * 两个输入一个输出
         */
        BiFunction < String, String, Integer> bf1 = (a, b) -> 1;
        System.out.println(bf1.apply("one", "two"));

        BiFunction<String, String, String> bf2 = (a, b) -> a + ", " + b;
        System.out.println(bf2.apply("Hello", "World"));
    }

    /**
     *
     */
    private static void functionDemo() throws Exception {
        /**
         * 五参无返回值
         */
        Runnable r1 = new Runnable() {
            public void run() {
                System.out.println("run1");
            }
        };
        r1.run();

        Runnable r2 = () -> { System.out.println("run2"); };
        r2.run();

        Runnable r3 = () -> System.out.println("run3");
        r3.run();

        /**
         * 无参有返回值
         */
        Callable<String> c1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "callable1";
            }
        };
        System.out.println(c1.call());

        Callable<String> c2 = () -> { return "callable2"; };
        System.out.println(c2.call());

        Callable<String> c3 = () -> "callable3";
        System.out.println(c3.call());

        /**
         * 有参无返回值
         */
        UserMapper u1 = new UserMapper() {
            @Override
            public void insert(User user) {
                System.out.println("insert user1: " + user);
            }
        };
        u1.insert(new User());

        UserMapper u2 = (usr) -> {
            System.out.println("insert user2: " + usr);
        };
        u2.insert(new User());

        UserMapper u3 = (User us) -> {
            System.out.println("insert user3: " + us);
        };
        u3.insert(new User());

        /**
         * 有参有返回值
         */
        OrderMapper o1 = new OrderMapper() {
            @Override
            public String insert(Order order) {
                return "order1: " + order;
            }
        };
        System.out.println(o1.insert(new Order()));

        OrderMapper o2 = (order) -> {
            return "order2: " + order;
        };
        System.out.println(o2.insert(new Order()));

        OrderMapper o3 = (Order od) -> {
            return "order3: " + od;
        };
        System.out.println(o3.insert(new Order()));

        OrderMapper o4 = (Order o) -> "order4: " + o;
        System.out.println(o4.insert(new Order()));

        /**
         * Function 一个输入一个输出
         */
        Function<Integer, Integer> f1 = a -> {
            int sum = 0;
            for (int i = 0; i < a; i++) {
                sum += i;
            }
            return sum;
        };
        System.out.println(f1.apply(10));
    }

    static String get() {
        return "Method get()";
    }

    static void exec() {
        System.out.println("run exec()");
    }
}

interface Foo {
    String get();
}

interface UserMapper {
    void insert(User user);
}

interface OrderMapper {
    String insert(Order order);
}

class User {

}

class Order {

}