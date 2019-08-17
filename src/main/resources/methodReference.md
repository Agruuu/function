# 方法的引用分类



| `类型`         | `语法`             | `对应的lambda表达式`                  |
| -------------- | ------------------ | ------------------------------------- |
| 静态方法的引用 | 类名::staticMethod | (args) -> 类名.staticMethod(args)     |
| 实例方法的引用 | Inst::instMethod   | (args) -> inst.instMethod(args)       |
| 对象方法的引用 | 类名::instMethod   | (inst, args) -> 类名.instMethod(args) |
| 构造方法的引用 | 类名::new          | (args) -> new 类名(args)              |


## 静态方法引用
```java
/**
 * 静态方法
 * staticClass::staticMethod
 * (a) -> staticClass.staticMethod(a);
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
```

## 实例方法引用
```java
/**
 * 实例方法引用
 * new instClass()::method
 * a -> new instClass().method(a)
 * 
 * instClass inst = new instClass();
 * inst::method
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
```

